package com.mask.task.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("jd_item")
@Data
public class Item {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("spu")
    private Long spu;
    @TableField("sku")
    private Long sku;
    @TableField("title")
    private String title;
    @TableField("price")
    private Float price;
    @TableField("pic")
    private String pic;
    @TableField("url")
    private String url;
    @TableField("created")
    private Date created;
    @TableField("updated")
    private Date updated;
}