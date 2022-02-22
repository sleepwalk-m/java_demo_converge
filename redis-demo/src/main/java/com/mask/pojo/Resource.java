package com.mask.pojo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 资源
 * </p>
 *
 */
@Data
@TableName("pd_auth_resource")
public class Resource {

    private static final long serialVersionUID = 1L;

    /**
     * 资源编码
     * 规则：
     * 链接：
     * 数据列：
     * 按钮：
     */
    @TableField(value = "code", condition = LIKE)
    private String code;

    /**
     * 接口名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 菜单ID
     * #pd_auth_menu
     */
    @TableField("menu_id")
    private Long menuId;

    /**
     * 接口描述
     */
    @TableField(value = "describe_", condition = LIKE)
    private String describe;

    @TableField(value = "method")
    private String method;

    @TableField(value = "url")
    private String url;
}
