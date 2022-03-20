package top.yanzx.cunzhao.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.yanzx.cunzhao.config.system.AliyunOssConfig;
import top.yanzx.cunzhao.util.model.StatusCode;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Description: 文件上传
 * @Author : 王俊
 * @date :  2020/11/28
 */
@Service
@Slf4j
public class FileUploadService {

    private static final String[] IMAGE_TYPE = new String[]{
            ".bmp",".jpg","jpeg",".gif",".png"
    };

    //注入阿里云oss文件服务器客户端
    @Resource
    private OSS ossClient;

    // 注入阿里云OSS基本配置类
    @Resource
    private AliyunOssConfig aliyunOssConfig;

    /**
     * 文件上传
     * @param uploadFile
     * @return
     */
    public String upload(MultipartFile uploadFile){
        String bucketName = aliyunOssConfig.getBucketName();    //bucket名称
        String accessKeyId = aliyunOssConfig.getAccessKeyId();
        String accessKeySecret = aliyunOssConfig.getAccessKeySecret();
        String endPoint = aliyunOssConfig.getEndPoint();        //地域节点
        String fileHost = aliyunOssConfig.getFileHost();        //目标文件夹
        //存储返回图片上传后的url
        String returnImgUrl = "";

        //校验图片格式
        boolean isLegal = false;
        for(String type : IMAGE_TYPE){
            //字符串以suffix结束 ,不区分大小写
            //uploadFile.getOriginalFilename() 返回原始文件名
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(),type));
            isLegal = true;
            break;
        }
        //如果图片格式不合法
        if (!isLegal){
            return StatusCode.ERROR.getMsg();
        }

        //获取文件原名称
        String originalFilename = uploadFile.getOriginalFilename();
        //获取文件类型
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        //新文件名称
        String newFileName = UUID.randomUUID().toString() + fileType;
        //构建日期路径, 例如：OSS目标文件夹/2020/10/31/文件名
        String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        // 文件上传的路径地址
        String uploadImageUrl = fileHost + "/" + filePath + "/" + newFileName;

        //获取文件输入流
        InputStream inputStream = null;
        try {
            inputStream = uploadFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 阿里云oss默认图片上传ContentType是image/jpeg
         * 也就是说 获取图片链接后 图片是下载链接 而并非在线浏览链接
         * 因此 这里在上传的时候要解决ContentType 的问题，将其改为image/jpeg
         */
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/jpg");
        metadata.setContentDisposition("inline");

        //文件上传到阿里云oss
        ossClient.putObject(bucketName,uploadImageUrl,inputStream,metadata);
        /**
         * 在实际项目开发中，文件上传成功后，数据库中会存储文件的地址
         */
        //获取文件上传后的图片返回地址
        returnImgUrl = "http://" + bucketName + "." + endPoint + "/" + uploadImageUrl;
        log.info("上传文件之后的地址：" + returnImgUrl);

        return returnImgUrl;
    }

    /**
     * 文件下载
     * @param fileName 文件名
     * @param response
     * @return
     */
    public String download(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        //文件名以附件的形式下载
        response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
        //获取oss的Bucket名称
        String bucketName = aliyunOssConfig.getBucketName();
        //获取oss目标文件夹
        String fileHost = aliyunOssConfig.getFileHost();
        //日期目录
        //这里写成固定获取日期目录的形式，逻辑上存在问题；实际上，filePath的日期目录应该是从数据库中查询
        String filePath = new DateTime().toString("yyyy/MM/dd");

        //files/20201130/fileName.jpg
        String fileKey = fileHost + "/" + filePath + "/" + fileName;
        //ossObject包含文件所在的存储空间名称，文件名称，文件元信息以及输入流
        OSSObject ossObject = ossClient.getObject(bucketName, fileKey);

        try {
            //通过流的方式读取文件内容
            InputStream inputStream = ossObject.getObjectContent();
            //把输入流放入缓存流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            ServletOutputStream outputStream = response.getOutputStream();
            //输出流放入缓存流
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = bufferedInputStream.read(buffer)) != -1){
                bufferedOutputStream.write(buffer,0,len);
            }
            //释放资源
            if(bufferedOutputStream != null){
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
            if(bufferedInputStream != null){
                bufferedInputStream.close();
            }
            return StatusCode.SUCCESS.getMsg();

        } catch (IOException e) {
            return StatusCode.ERROR.getMsg();
        }
    }

    /***
     * 文件删除
     * @param fileName 文件名称
     * @return 删除状态
     */
    public String delete(String fileName){
        //获取到oss的Bucket名称
        String bucletName = aliyunOssConfig.getBucketName();
        //获取oss地域节点
        String endPoint = aliyunOssConfig.getEndPoint();
        //获取oss的AccessKeyId
        String accessKeyId = aliyunOssConfig.getAccessKeyId();
        //获取oss的AccessKeySecret
        String accessKeySecret = aliyunOssConfig.getAccessKeySecret();
        //获取oss目标文件夹
        String fileHost = aliyunOssConfig.getFileHost();
        //日期目录
        //这里写成固定获取日期目录的形式，逻辑上存在问题；实际上，filePath的日期目录应该是从数据库中查询
        String filePath = new DateTime().toString("yyyy/MM/dd");

        try {
            /**
             * 实际上不会删除OSS文件服务器中的文件，只需要删除数据库中的文件路径即可
             */
            //不是采用@Bean的方式，而是创建，因为容易报错Connection pool shut down
            OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            //根据BucketName和fileName删除文件
            //删除目录中的文件，如果是最后一个文件，filePath目录会被删除
            String fileKey = fileHost + "/" + filePath + "/" + fileName;
            ossClient.deleteObject(bucletName,fileKey);

            try {

            }finally {
                ossClient.shutdown();
            }
            System.out.println("文件删除！");
            return StatusCode.SUCCESS.getMsg();
        }catch (Exception e){
            return StatusCode.ERROR.getMsg();
        }
    }

}
