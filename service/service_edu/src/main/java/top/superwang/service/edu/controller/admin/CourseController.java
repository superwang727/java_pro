package top.superwang.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.superwang.common.base.result.R;
import top.superwang.service.edu.entity.Course;
import top.superwang.service.edu.entity.Teacher;
import top.superwang.service.edu.entity.form.CourseInfoForm;
import top.superwang.service.edu.entity.vo.CoursePublishVo;
import top.superwang.service.edu.entity.vo.CourseQueryVo;
import top.superwang.service.edu.entity.vo.CourseVo;
import top.superwang.service.edu.entity.vo.TeacherQueryVo;
import top.superwang.service.edu.service.CourseService;

import java.util.List;

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




    @ApiOperation("课程信息分页")
    @GetMapping("listPage/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码",required = true) @PathVariable Long page,
                      @ApiParam(value = "每页多少数据", required = true) @PathVariable Long limit,
                      @ApiParam("查询对象") CourseQueryVo courseQueryVo){


        IPage<CourseVo> pageModel = courseService.selectPage(page, limit, courseQueryVo);

        List<CourseVo> records = pageModel.getRecords(); // 当前数据

        long total = pageModel.getTotal();  // 总页码

        return R.ok().data("totals",total).data("datalist",records);

    }


    @ApiOperation(value = "根据id删除课程",notes = "输入一个id，然后删！")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam("课程id") @PathVariable String id){

        // 删除封面
        courseService.removeCoverById(id);

        // 删除课程
        boolean b = courseService.removeCourseById(id);

        if (b){
            return R.ok().message("删除成功！");
        }else {
            return R.error().message("删除失败！");
        }

    }

    @ApiOperation("根据ID获取课程发布信息")
    @GetMapping("course-publish/{id}")
    public R getCoursePublishVoById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id){

        CoursePublishVo coursePublishVo = courseService.getCoursePublishVoById(id);
        if (coursePublishVo != null) {
            return R.ok().data("datalist", coursePublishVo);
        } else {
            return R.error().message("数据不存在");
        }
    }



    @ApiOperation("根据课程id发布课程")
    @PutMapping("put-publish-course/{id}")
    public R putPublishCourseById(
            @ApiParam(value = "课程id",required = true)
            @PathVariable String id
    ){
        boolean b = courseService.patchPublishCourseById(id);

        if (b){
            return R.ok().message("发布成功");
        }else {
            return R.error().message("发布失败");
        }
    }





}

