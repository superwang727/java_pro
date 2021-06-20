package top.superwang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import top.superwang.common.base.result.R;
import top.superwang.service.edu.entity.Teacher;
import top.superwang.service.edu.entity.vo.TeacherQueryVo;
import top.superwang.service.edu.feign.OssFileService;
import top.superwang.service.edu.mapper.TeacherMapper;

import top.superwang.service.edu.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {


    @Qualifier("top.superwang.service.edu.feign.OssFileService")
    @Autowired
    private OssFileService ossFileService;


    @Override
    public IPage<Teacher> selectPage(Page<Teacher> pageParams, TeacherQueryVo teacherQueryVo) {

//        1.排序，根据sort字段排序
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByAsc("sort");

        // 查询的结果不是是null，就返回分页
        if (teacherQueryVo==null){
            return baseMapper.selectPage(pageParams,teacherQueryWrapper);
        }

        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();

        if (!StringUtils.isEmpty(name)){
            teacherQueryWrapper.likeRight("name",name);
        }

        if (level!=null){
            teacherQueryWrapper.eq("level",level);

        }
        if (!StringUtils.isEmpty(joinDateBegin)){
            teacherQueryWrapper.ge("join_date",joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)){
            teacherQueryWrapper.le("end_date",joinDateEnd);
        }



        return baseMapper.selectPage(pageParams,teacherQueryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectTeacherByName(String key) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>(); // 查询对象
        teacherQueryWrapper.select("name");  // 查询name列
        teacherQueryWrapper.likeRight("name",key);  // select name from edu_teacher where name like "王%";

        return baseMapper.selectMaps(teacherQueryWrapper);
    }

    @Override
    public boolean removeAvatarById(String id) {
        // 根据id查到数据库的头像地址
        Teacher teacher = baseMapper.selectById(id);

        if (teacher != null){
            String avatar = teacher.getAvatar();
            if (!StringUtils.isEmpty(avatar)){
                R r = ossFileService.removeAvatar(avatar);
                return r.getSuccess();
            }
        }

        return false;


    }
}
