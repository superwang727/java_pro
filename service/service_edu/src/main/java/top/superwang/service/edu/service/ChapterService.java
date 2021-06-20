package top.superwang.service.edu.service;

import org.springframework.stereotype.Repository;
import top.superwang.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import top.superwang.service.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
@Repository
public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVo> getChapterNestedList(String courseId);
}
