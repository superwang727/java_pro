package top.superwang.service.oss.util;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss") // 读取properties文件
public class OssProperties {

    private String endpoint;
    private String keyid;
    private String keysecret;
    private String bucketName;

}
