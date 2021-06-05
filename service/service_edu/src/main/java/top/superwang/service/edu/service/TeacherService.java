package top.superwang.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.superwang.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import top.superwang.service.edu.entity.vo.TeacherQueryVo;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
public interface TeacherService extends IService<Teacher> {

    IPage<Teacher> selectPage(Page<Teacher> pageParams, TeacherQueryVo teacherQueryVo);


}
