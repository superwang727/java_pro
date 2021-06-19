package top.superwang.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;
import top.superwang.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import top.superwang.service.edu.entity.Teacher;
import top.superwang.service.edu.entity.form.CourseInfoForm;
import top.superwang.service.edu.entity.vo.CoursePublishVo;
import top.superwang.service.edu.entity.vo.CourseQueryVo;
import top.superwang.service.edu.entity.vo.CourseVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
@Repository
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo);

    boolean removeCoverById(String id);

    boolean removeCourseById(String id);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean patchPublishCourseById(String id);
}
