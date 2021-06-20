package top.superwang.service.edu.mapper;

import org.springframework.stereotype.Repository;
import top.superwang.service.edu.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

}
