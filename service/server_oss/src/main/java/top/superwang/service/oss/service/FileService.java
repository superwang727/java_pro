package top.superwang.service.oss.service;

import java.io.InputStream;

public interface FileService {

    // 文件流   上传到哪个目录   图片的原始名字
    String upload(InputStream inputStream, String module, String originalFilename);

}
