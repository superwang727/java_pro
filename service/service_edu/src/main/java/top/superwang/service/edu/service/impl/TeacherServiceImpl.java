package top.superwang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import top.superwang.service.edu.entity.Teacher;
import top.superwang.service.edu.entity.vo.TeacherQueryVo;
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

        List<Map<String, Object>> maps = baseMapper.selectMaps(teacherQueryWrapper);
        return maps;
    }
}
