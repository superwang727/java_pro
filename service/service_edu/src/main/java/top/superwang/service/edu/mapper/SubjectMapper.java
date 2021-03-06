package top.superwang.service.edu.mapper;

import org.springframework.stereotype.Repository;
import top.superwang.service.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.superwang.service.edu.entity.vo.SubjectVo;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
@Repository
public interface SubjectMapper extends BaseMapper<Subject> {

    List<SubjectVo> selectNestedDataById(String parentId);
}
