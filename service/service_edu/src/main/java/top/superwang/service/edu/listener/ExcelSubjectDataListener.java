package top.superwang.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.superwang.service.edu.entity.Subject;
import top.superwang.service.edu.entity.excel.ExcelSubjectData;
import top.superwang.service.edu.mapper.SubjectMapper;

@Slf4j
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 全参构造
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData> {


    private SubjectMapper subjectMapper;

//    遍历每一行数据
    @Override
    public void invoke(ExcelSubjectData data, AnalysisContext analysisContext) {
          log.info("解析到一条数据：{}",data);
        String levelOneTitle = data.getLevelOneTitle();
        String levelTwoTitle = data.getLevelTwoTitle();
        log.info("levelOneTitle：{}",levelOneTitle);
        log.info("levelTwoTitle：{}",levelTwoTitle);

        Subject subjectLevelOne = this.isLevelOneTitleExist(levelOneTitle);
        String parentId = null;
        if (subjectLevelOne == null){
            // 插入一级分类
            Subject subject = new Subject();
            subject.setTitle(levelOneTitle);
            subject.setParentId("0");
            // 存入数据库
            subjectMapper.insert(subject);
            parentId = subject.getId();

        }else {
            parentId = subjectLevelOne.getId();
        }

        Subject levelTwoTitleExist = this.isLevelTwoTitleExist(levelTwoTitle, parentId);

        if (levelTwoTitleExist == null){
            Subject subject = new Subject();
            subject.setTitle(levelTwoTitle);
            subject.setParentId(parentId);
            subjectMapper.insert(subject);
        }

    }

    // 数据的收尾工作
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("全部数据解析完成！");

    }

    private Subject isLevelOneTitleExist(String levelOne){
        // 查询一下一级标题是否重复了，如果能查到就返回一个对象，没有就返回null
        QueryWrapper<Subject> subjectQueryWrapper = new QueryWrapper<>();
        subjectQueryWrapper.eq("title",levelOne);
        subjectQueryWrapper.eq("parent_id","0");
        return subjectMapper.selectOne(subjectQueryWrapper);

    }

    private Subject isLevelTwoTitleExist(String levelOne, String parentId){
        // 查询一下一级标题是否重复了，如果能查到就返回一个对象，没有就返回null
        QueryWrapper<Subject> subjectQueryWrapper = new QueryWrapper<>();
        subjectQueryWrapper.eq("title",levelOne);
        subjectQueryWrapper.eq("parent_id",parentId);
        return subjectMapper.selectOne(subjectQueryWrapper);

    }


}
