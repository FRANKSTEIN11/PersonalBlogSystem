package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author
 */
@Data
public class Thumbsup implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer thumbsupId;

    private Integer articleId;

    private Integer userId;

    @TableField(exist = false)
    private String uuid;

    private static final long serialVersionUID = 1L;
}