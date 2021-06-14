package top.superwang.service.edu.service;

import top.superwang.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import top.superwang.service.edu.entity.vo.SubjectVo;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
public interface SubjectService extends IService<Subject> {

    void bathImport(InputStream inputStream);

    List<SubjectVo> nestedSubjectData();

}
