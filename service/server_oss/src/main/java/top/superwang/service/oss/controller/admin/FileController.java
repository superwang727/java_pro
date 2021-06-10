package top.superwang.service.oss.controller.admin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import top.superwang.common.base.result.R;
import top.superwang.common.base.result.ResultCode;
import top.superwang.service.base.exception.EduException;
import top.superwang.service.oss.service.FileService;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@Api(tags = "阿里云文件管理")
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {


    @Autowired
    private FileService fileService;


    @ApiOperation("文件上传")
    @PostMapping("upload")
    public R upload(@ApiParam(value = "文件",required = true) @RequestParam("file") MultipartFile file,
                    @ApiParam(value = "文件夹",required = true) @RequestParam("module") String module)  {

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();

            String fileUrl = fileService.upload(inputStream, module, originalFilename);

            return R.ok().data("fileUrl",fileUrl);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new EduException(ResultCode.UPLOAD_ERROR);
        }

    }


    @ApiOperation(value = "删除头像")
    @DeleteMapping("removeAvatar")
    public R removeAvatar(
            @ApiParam(value = "要删除的头像url",required = true)
            @RequestBody String url){
        fileService.removeAvatar(url);
        return R.ok().message("文件删除成功");
    }

    @ApiOperation(value = "测试")
    @GetMapping("test")
    public R test(){
        log.info("oss  test 被调用");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok();
    }



}
