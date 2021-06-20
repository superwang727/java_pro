package top.superwang.service.edu.controller.admin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.superwang.common.base.result.R;
import top.superwang.service.edu.entity.Chapter;
import top.superwang.service.edu.entity.vo.ChapterVo;
import top.superwang.service.edu.service.ChapterService;

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
@Api(tags = "章节管理接口")
@Slf4j
@RestController
@RequestMapping("/admin/edu/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;


    @ApiOperation("保存章节对象")
    @PostMapping("save")
    public R save(
            @ApiParam(value = "章节对象", required = true)
            @RequestBody Chapter character
    ) {
        boolean c = chapterService.save(character);
        if (c) {
            return R.ok().message("保存成功");
        } else {
            return R.error().message("保存失败");
        }
    }

    @ApiOperation("根据id查询章节")
    @GetMapping("get/{id}")
    public R getById(
            @ApiParam(value = "章节id", required = true)
            @PathVariable String id) {

        Chapter chapter = chapterService.getById(id);
        if (chapter != null) {
            return R.ok().data("item", chapter);
        } else {
            return R.error().message("数据不存在");
        }
    }


    @ApiOperation("根据id修改章节")
    @PutMapping("update")
    public R updateById(
            @ApiParam(value = "章节对象", required = true)
            @RequestBody Chapter chapter) {

        boolean result = chapterService.updateById(chapter);
        if (result) {
            return R.ok().message("修改成功");
        } else {
            return R.error().message("数据不存在");
        }
    }


    @ApiOperation("根据id删除")
    @DeleteMapping("remove/{id}")
    public R deleteById(@ApiParam(value = "章节id", required = true) @PathVariable String id) {

        // TODO : 根据id删除视频video


        boolean b = chapterService.removeChapterById(id);

        if (b) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }

    }

    @ApiOperation("根据courseId获取嵌套章节和视频信息")
    @GetMapping("nested-list/{courseId}")
    public R nestedList(@ApiParam(value = "课程id", required = true) @PathVariable String courseId) {

        List<ChapterVo> chapterVos= chapterService.getChapterNestedList(courseId);

        return R.ok().data("datalist",chapterVos);

    }




}

