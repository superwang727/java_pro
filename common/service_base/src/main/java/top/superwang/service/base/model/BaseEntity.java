package top.superwang.service.base.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author wangw
 * @since 2021-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师ID")
    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "创建时间", example = "2019-10-30 14:18:56")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间",example = "2019-10-30 14:19:02")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}