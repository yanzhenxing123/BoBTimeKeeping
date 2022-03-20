package top.yanzx.cunzhao.config.system;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author : 王俊
 * @date :  2020/11/28
 */
@Configuration
//@ConfigurationProperties(prefix = "aliyun")
@Data
@Accessors(chain = true)
public class AliyunOssConfig {



    @Value("${aliyun.endPoint}")
    private String endPoint;    //地域节点
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId; //
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.bucketName}")
    private String bucketName;  //bucketc名称
    @Value("${aliyun.fileHost}")
    private String fileHost;    //目标文件夹

    @Bean
    public OSS OSSClient(){
        return new OSSClient(endPoint,accessKeyId,accessKeySecret);
    }
}
