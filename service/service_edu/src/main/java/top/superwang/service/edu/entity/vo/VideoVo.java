package top.superwang.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free; // 视频是否免费
    private Integer sort;

    private String videoSourceId;
}
