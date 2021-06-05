package top.superwang.service.edu.service.impl;

import top.superwang.service.edu.entity.Comment;
import top.superwang.service.edu.mapper.CommentMapper;
import top.superwang.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
