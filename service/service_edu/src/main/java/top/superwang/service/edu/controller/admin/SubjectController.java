package top.superwang.service.edu.controller.admin;


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
import top.superwang.service.edu.entity.Subject;
import top.superwang.service.edu.entity.vo.SubjectVo;
import top.superwang.service.edu.service.SubjectService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
@CrossOrigin// 防止跨域问题
@Api(tags = "课程分类管理")
@Slf4j
@RestController
@RequestMapping("/admin/edu/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("文件导入")
    @PostMapping("import")
    public R batchImpot(@ApiParam(value = "文件",required = true)
                            @RequestParam("file") MultipartFile file){

        try {
            InputStream inputStream = file.getInputStream();
            subjectService.bathImport(inputStream);
            return R.ok().message("文件批量上传成功");
        } catch (Exception e) {
            log.info(ExceptionUtils.getMessage(e));
            throw new EduException(ResultCode.UPLOAD_EXCEL_ERROR);
        }

    }



    @ApiOperation("嵌套课程数据")
    @GetMapping("nested")
    public R nestedList(){

        List<SubjectVo> subjects = subjectService.nestedSubjectData();

        return R.ok().data("datalist",subjects);
    }

}

