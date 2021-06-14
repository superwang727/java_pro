package top.superwang.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import top.superwang.service.edu.entity.Subject;
import top.superwang.service.edu.entity.excel.ExcelSubjectData;
import top.superwang.service.edu.entity.vo.SubjectVo;
import top.superwang.service.edu.listener.ExcelSubjectDataListener;
import top.superwang.service.edu.mapper.SubjectMapper;
import top.superwang.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author wangw
 * @since 2021-04-18
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void bathImport(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelSubjectData.class,new ExcelSubjectDataListener(baseMapper))
                .excelType(ExcelTypeEnum.XLS)
                .sheet().doRead();
    }

    @Override
    public List<SubjectVo> nestedSubjectData() {
        return baseMapper.selectNestedDataById("0");
    }
}
