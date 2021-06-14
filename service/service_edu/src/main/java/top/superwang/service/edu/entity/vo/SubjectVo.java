package top.superwang.service.edu.entity.vo;

import lombok.Data;
import top.superwang.service.edu.entity.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;

    //自关联嵌套
    private List<SubjectVo> children = new ArrayList<>();

}
