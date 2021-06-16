package top.superwang.service.edu.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.superwang.service.edu.entity.Course;
import top.superwang.service.edu.entity.CourseDescription;
import top.superwang.service.edu.entity.form.CourseInfoForm;
import top.superwang.service.edu.mapper.CourseDescriptionMapper;
import top.superwang.service.edu.mapper.CourseMapper;
import top.superwang.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        // 保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        course.setStatus(Course.COURSE_DRAFT);
        baseMapper.insert(course);  //这里保存课程后，主键的id会自动回填

        // 保存课程的描述
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());   // 上面主键回填了，这里才能获取到id
        courseDescriptionMapper.insert(courseDescription);

        return course.getId();

    }
}
