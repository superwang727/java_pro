package top.superwang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.superwang.service.edu.entity.Chapter;
import top.superwang.service.edu.entity.Video;
import top.superwang.service.edu.entity.vo.ChapterVo;
import top.superwang.service.edu.entity.vo.VideoVo;
import top.superwang.service.edu.mapper.ChapterMapper;
import top.superwang.service.edu.mapper.VideoMapper;
import top.superwang.service.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {


    @Autowired
    private VideoMapper videoMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeChapterById(String id) {

        // 删除视频表信息
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", id);
        videoMapper.delete(videoQueryWrapper);


        return this.removeById(id);


    }

    @Override
    public List<ChapterVo> getChapterNestedList(String courseId) {

        // 在逻辑层实现数据的嵌套 和 在数据库层实现数据的嵌套

        // 1.在数据库层,首先根据课程id查询到章节列表,再根据每一个章节的id去查video.  1+n的sql,效率不行

        // 2.在逻辑层解决,因为video表已经有了冗余字段course_id,直接查到所有章节列表,再查到所有视频列表,1+1的sql

        // 查询所有章节信息
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        chapterQueryWrapper.orderByAsc("sort","id"); // 根据sort排序,重复根据id排
        List<Chapter> chapters = baseMapper.selectList(chapterQueryWrapper);

        // 查询所有视频信息
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        videoQueryWrapper.orderByAsc("sort","id");
        List<Video> videos = videoMapper.selectList(videoQueryWrapper);

        List<ChapterVo> chapterVoList = new ArrayList<>();
        // 组装数据
        for (int i = 0; i < chapters.size(); i++) {
            Chapter chapter = chapters.get(i);

            // 查到所有章节信息了,不需要这么多字段,把属性copy到chapterVo里面去
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            chapterVoList.add(chapterVo);

            // 遍历video数据
            ArrayList<VideoVo> videoVoList = new ArrayList<>();
            for (int j = 0; j < videos.size(); j++) {
                Video video = videos.get(j);
                if (chapter.getId().equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);

                     // BeanUtils遇到布尔值会失效,坑!
                    videoVo.setFree(video.getIsFree());

                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);


        }
        return chapterVoList;




    }
}
