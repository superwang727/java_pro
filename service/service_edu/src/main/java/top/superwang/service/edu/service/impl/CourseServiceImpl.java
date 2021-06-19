package top.superwang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import top.superwang.common.base.result.R;
import top.superwang.service.edu.entity.*;
import top.superwang.service.edu.entity.form.CourseInfoForm;
import top.superwang.service.edu.entity.vo.CoursePublishVo;
import top.superwang.service.edu.entity.vo.CourseQueryVo;
import top.superwang.service.edu.entity.vo.CourseVo;
import top.superwang.service.edu.feign.OssFileService;
import top.superwang.service.edu.mapper.*;
import top.superwang.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Autowired
    private OssFileService ossFileService;


    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CourseCollectMapper courseCollectMapper;





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

    @Override
    public CourseInfoForm getCourseInfoById(String id) {
        // 根据id查课程基本信息
        Course course = baseMapper.selectById(id);

        if (course == null){return null;}

        //根据id查课程描述
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);

        // 上面两个组装
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course,courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());

        return courseInfoForm;

    }

    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        // 保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        baseMapper.updateById(course);  //这里保存课程后，主键的id会自动回填

        // 保存课程的描述
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(courseInfoForm.getId());   // 上面主键回填了，这里才能获取到id
        courseDescriptionMapper.updateById(courseDescription);

    }

    @Override
    public IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo) {

        // 组装查询条件
        QueryWrapper<CourseVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("c.gmt_create");  //排序

        String title = courseQueryVo.getTitle();
        String teacherId = courseQueryVo.getTeacherId();
        String subjectParentId = courseQueryVo.getSubjectParentId();
        String subjectId = courseQueryVo.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("c.title", title);
        }

        if (!StringUtils.isEmpty(teacherId) ) {
            queryWrapper.eq("c.teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("c.subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("c.subject_id", subjectId);
        }

        // 组装分页
        Page<CourseVo> pageParams = new Page<>(page, limit);
        // 这个分页参数传进去是为了在sql最后插入个分页，传入就行，mp自动执行
        List<CourseVo> records =  baseMapper.selectPageByQueryvo(pageParams,queryWrapper);

        pageParams.setRecords(records); // 查询下total数据

        return pageParams;


    }

    @Override
    public boolean removeCoverById(String id) {
        // 根据id查到数据库的封面地址
        Course course = baseMapper.selectById(id);

        if (course != null){
            String cover = course.getCover();
            if (!org.apache.commons.lang.StringUtils.isEmpty(cover)){
                R r = ossFileService.removeAvatar(cover);
                return r.getSuccess();
            }
        }

        return false;
    }

    @Override
    public boolean removeCourseById(String id) {

        //收藏信息：course_collect
        QueryWrapper<CourseCollect> courseCollectQueryWrapper = new QueryWrapper<>();
        courseCollectQueryWrapper.eq("course_id", id);
        courseCollectMapper.delete(courseCollectQueryWrapper);

        //评论信息：comment
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("course_id", id);
        commentMapper.delete(commentQueryWrapper);

        //课时信息：video
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", id);
        videoMapper.delete(videoQueryWrapper);

        //章节信息：chapter
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", id);
        chapterMapper.delete(chapterQueryWrapper);

        //课程详情：course_description
        courseDescriptionMapper.deleteById(id);

        //课程信息：course
        return this.removeById(id);


    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {

        return baseMapper.selectPublishById(id);
    }

    @Override
    public boolean patchPublishCourseById(String id) {

        Course course = new Course();

        course.setId(id);
        course.setStatus(Course.COURSE_NORMAL);

        return this.updateById(course);


    }


}
