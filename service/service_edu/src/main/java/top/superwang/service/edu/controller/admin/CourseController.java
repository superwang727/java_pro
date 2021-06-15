package top.superwang.service.edu.controller.admin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.superwang.common.base.result.R;
import top.superwang.service.edu.entity.form.CourseInfoForm;
import top.superwang.service.edu.service.CourseService;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
@CrossOrigin// 防止跨域问题
@Api(tags = "新增课程的接口")
@Slf4j
@RestController
@RequestMapping("/admin/edu/course")
public class CourseController {


    @Autowired
    private CourseService courseService;


    @PostMapping("add-course")
    public R courseSaveInfo(
            @ApiParam(value = "前端传入的课程的基本信息",readOnly = true)
            @RequestBody CourseInfoForm courseInfoForm
            ){
        String courseId = courseService.saveCourseInfo(courseInfoForm);

        return R.ok().data("courseId",courseId).message("保存课程成功");
    }

}

