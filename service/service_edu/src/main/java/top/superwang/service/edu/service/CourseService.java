package top.superwang.service.edu.service;

import top.superwang.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import top.superwang.service.edu.entity.form.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);
}
