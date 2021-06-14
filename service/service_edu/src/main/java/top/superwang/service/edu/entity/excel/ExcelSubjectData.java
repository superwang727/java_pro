package top.superwang.service.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelSubjectData {

    @ExcelProperty("一级分类")
    private String levelOneTitle;

    @ExcelProperty("二级分类")
    private String levelTwoTitle;

}
