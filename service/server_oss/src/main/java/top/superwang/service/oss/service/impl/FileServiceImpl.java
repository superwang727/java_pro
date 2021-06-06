package top.superwang.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import top.superwang.service.oss.service.FileService;
import top.superwang.service.oss.util.OssProperties;

import java.io.InputStream;
import java.util.UUID;

public class FileServiceImpl implements FileService {

    @Autowired
    private OssProperties ossProperties;


    @Override
    public String upload(InputStream inputStream, String module, String originalFilename) {

        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        String bucketName = ossProperties.getBucketName();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);

        // 如果bucket不存在就创建一个
        if (!ossClient.doesBucketExist(bucketName)){
            ossClient.createBucket(bucketName);
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }


        // 构建文件路径    avatar/2021/06/06/default_handsome.jpg
        String folder = new DateTime().toString("yyyy/MM/dd");  // 日期路径

        String fileName = UUID.randomUUID().toString();   // 用uuid来作为图片文件的文件名
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".")); // 文件后缀

        String key = module + "/" + folder + "/" + fileName + fileSuffix;

        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, key, inputStream);



        // 关闭OSSClient。
        ossClient.shutdown();


        // https://edu-zaixian-avatar.oss-cn-shanghai.aliyuncs.com/avatar/default_handsome.jpg
        return "https://" + bucketName + "." + endpoint + "/" + key;
    }
}
