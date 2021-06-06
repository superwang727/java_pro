package top.superwang.service.oss.controller.admin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.superwang.common.base.result.R;
import top.superwang.service.oss.service.FileService;


import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin
@Api(tags = "阿里云文件管理")
@RequestMapping("/admin/oss/file")
public class FileController {


    @Autowired
    private FileService fileService;


    @ApiOperation("文件上传")
    @PostMapping("upload")
    public R upload(@ApiParam(value = "文件",required = true) @RequestParam("file") MultipartFile file,
                    @ApiParam(value = "文件夹",required = true) @RequestParam("module") String module) throws IOException {

        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();

        String fileUrl = fileService.upload(inputStream, module, originalFilename);

        return R.ok().data("fileUrl",fileUrl);
    }
}
