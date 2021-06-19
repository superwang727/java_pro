package top.superwang.service.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.superwang.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.superwang.service.edu.entity.vo.CoursePublishVo;
import top.superwang.service.edu.entity.vo.CourseVo;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVo> selectPageByQueryvo(
            Page<CourseVo> pageParams,
            @Param(Constants.WRAPPER) QueryWrapper<CourseVo> queryWrapper);


    CoursePublishVo selectPublishById(String id);
}
