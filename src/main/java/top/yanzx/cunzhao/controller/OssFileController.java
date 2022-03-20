package top.yanzx.cunzhao.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.yanzx.cunzhao.service.FileUploadService;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @Description: oss文件上传控制器
 * @Author : 王俊
 * @date :  2020/11/30
 */
//@Api(description = "阿里云oss文件上传、下载、删除API")
@RequestMapping("api/file")
@RestController
public class OssFileController {

    @Autowired
    private FileUploadService fileUploadService;


    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public JSONObject upload(@RequestParam("file") MultipartFile file){
        JSONObject jsonObject = new JSONObject();
        if(file != null){
            String returnFileUrl = fileUploadService.upload(file);
            if(returnFileUrl.equals("error")){
                jsonObject.put("error","文件上传失败！");
                return jsonObject;
            }
            jsonObject.put("success","文件上传成功！");
            jsonObject.put("returnFileUrl",returnFileUrl);
            return jsonObject;
        }else {
            jsonObject.put("error","文件上传失败！");
            return jsonObject;
        }
    }

    /**
     * 文件下载
     * @param fileName 文件名
     * @param response
     * @return
     */
    @GetMapping("/download/{fileName}")
    public JSONObject download(@PathVariable("fileName") String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        JSONObject jsonObject = new JSONObject();

        String status = fileUploadService.download(fileName, response);
        if(status.equals("error")){
            jsonObject.put("error","文件下载失败！");
            return jsonObject;
        }else {
            jsonObject.put("success","文件上传成功！");
            return jsonObject;
        }
    }

    /**
     * 文件删除
     * @param fileName 文件名称
     * @return
     */
    @GetMapping("/delete/{fileName}")
    public JSONObject delete(@PathVariable("fileName") String fileName){
        JSONObject jsonObject = new JSONObject();

        String status = fileUploadService.delete(fileName);
        if(status.equals("error")){
            jsonObject.put("error","文件删除失败！");
            return jsonObject;
        }else {
            jsonObject.put("success","文件删除成功！");
            return jsonObject;
        }
    }
}
