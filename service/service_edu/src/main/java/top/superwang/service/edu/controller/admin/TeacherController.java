package top.superwang.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import top.superwang.common.base.result.R;
import top.superwang.service.edu.entity.Teacher;
import top.superwang.service.edu.entity.vo.TeacherQueryVo;
import top.superwang.service.edu.feign.OssFileService;
import top.superwang.service.edu.service.TeacherService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
@CrossOrigin// 防止跨域问题
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @Autowired
    private OssFileService ossFileService;

//    @ApiOperation("获取所有教师列表")
//    @GetMapping("list")
//    public List<Teacher> listAll(){
//        List<Teacher> list = teacherService.list(null);
//        return list;
//
//    }

    // 返回统一的格式
    @ApiOperation("获取所有教师列表")
    @GetMapping("list")
    public R listAll(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("datalist",list);

    }


//    @ApiOperation(value = "根据id删除老师",notes = "输入一个id，然后删！")
//    @DeleteMapping("remove/{id}")
//    public boolean removeById(@ApiParam("老师id") @PathVariable String id){
//      return teacherService.removeById(id);
//    }

    @ApiOperation(value = "根据id删除老师",notes = "输入一个id，然后删！")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam("老师id") @PathVariable String id){


        boolean b = teacherService.removeById(id);

        if (b){
            return R.ok().message("删除成功！");
        }else {
            return R.error().message("删除失败！");
        }

    }

    @ApiOperation("讲师信息分页")
    @GetMapping("listPage/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码",required = true) @PathVariable Long page,
                      @ApiParam(value = "每页多少数据", readOnly = true) @PathVariable Long limit,
                      @ApiParam("查询对象")TeacherQueryVo teacherQueryVo){

        // 新建个Page对象，接收当前页和当前数据
        Page<Teacher> pageParams = new Page<>(page, limit);

        // page方法接收查询参数，和查询对象,对象没有就null
//        IPage<Teacher> pageModel = teacherService.page(pageParams, null);

        // 根据条件查询数据，这个selectPage是自己写的方法.实现方法在service.impl.TeacherServiceimpl
        IPage<Teacher> pageModel = teacherService.selectPage(pageParams, teacherQueryVo);


        List<Teacher> records = pageModel.getRecords(); // 当前数据

        long total = pageModel.getTotal();  // 总页码

        return R.ok().data("totals",total).data("datalist",records);



    }


    @ApiOperation("新增讲师")
    @PostMapping("save")
    public R saveTeacher(@ApiParam("讲师对象") @RequestBody Teacher teacher){  // Requestbody是接收json数据
        teacherService.save(teacher);
        return R.ok().message("保存成功");
    }


    @ApiOperation("根据id修改讲师信息")
    @PutMapping("update")
    public R updateById(@ApiParam("讲师对象") @RequestBody Teacher teacher){  // 讲师对象必须包含id信息
        boolean b = teacherService.updateById(teacher);
        if (b){
            return R.ok().message("保存成功");
        }else {
            return R.error().message("修改失败");
        }
    }



    @ApiOperation("根据id获取讲师信息")
    @GetMapping("get/{id}")
    public R updateById(@ApiParam("讲述id") @PathVariable String id){  // 讲师对象必须包含id信息
        Teacher t = teacherService.getById(id);
        if (t != null){
            return R.ok().data("datalist",t);
        }else {
            return R.error().message("数据不存在");
        }
    }



    @ApiOperation(value = "根据列表删除老师",notes = "批量删除老师")
    @DeleteMapping("batchRemoveTeachers")
    public R batchRemoveByIds(@ApiParam(value = "老师id列表",readOnly = true) @RequestBody List<String> ids){


        boolean b = teacherService.removeByIds(ids);

        if (b){
            return R.ok().message("删除成功！");
        }else {
            return R.error().message("删除失败！");
        }

    }


    @ApiOperation(value = "根据姓名查老师",notes = "根据姓名查老师名字")
    @GetMapping("findTeacherByName/name/{key}")
    public R findTeacherByName(@ApiParam(value = "老师id列表",readOnly = true) @PathVariable String key){

        // {"王":"王杰","王":"王结论"}

        List<Map<String,Object>> nameList = teacherService.selectTeacherByName(key);

        return R.ok().data("nameList",nameList);

    }


    @ApiOperation(value = "测试服务之间的调用")
    @GetMapping("test")
    public R test(){
        ossFileService.test();
        return R.ok();
    }




}

