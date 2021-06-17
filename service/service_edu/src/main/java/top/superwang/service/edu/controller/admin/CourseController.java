package top.superwang.service.edu.controller.admin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.superwang.common.base.result.R;
import top.superwang.service.edu.entity.Course;
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
@Api(tags = "课程管理接口")
@Slf4j
@RestController
@RequestMapping("/admin/edu/course")
public class CourseController {


    @Autowired
    private CourseService courseService;


    @ApiOperation("新增课程信息")
    @PostMapping("add-course")
    public R courseSaveInfo(
            @ApiParam(value = "前端传入的课程的基本信息",readOnly = true)
            @RequestBody CourseInfoForm courseInfoForm
            ){
        String courseId = courseService.saveCourseInfo(courseInfoForm);

        return R.ok().data("courseId",courseId).message("保存课程成功");
    }


    @ApiOperation("根据id查询课程所有信息")
    @GetMapping("course-info/{id}")
    public R selectCourseFormById(@ApiParam(value = "课程id", required = true) @PathVariable String id){

      CourseInfoForm courseInfoForm =  courseService.getCourseInfoById(id);

      if (courseInfoForm !=null){
          return R.ok().data("datalist",courseInfoForm);
      }else {
          return R.error().message("数据不存在");
      }

    }

    @ApiOperation("根据id修改课程信息")
    @PutMapping("update-course-info")
    public R updateCourseById(@ApiParam(value = "课程对象", required = true)
                                  @RequestBody CourseInfoForm courseInfoForm){

        courseService.updateCourseInfoById(courseInfoForm);

        return R.ok().message("更新成功");

    }



}

