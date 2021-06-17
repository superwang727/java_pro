package top.superwang.service.edu.entity.vo;


//查询条件的对象

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 前端4个查询条件，根据标题、讲师、一级分类、二级分类
    private String title;
    private String teacherId;
    private String subjectParentId;
    private String subjectId;
}