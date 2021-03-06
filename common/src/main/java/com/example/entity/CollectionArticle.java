package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author
 */
@Data
@TableName(value = "collection")
public class CollectionArticle implements Serializable {
    private Integer articleId;

    private Integer userId;

    @TableField(exist = false)
    private String uuid;

    private static final long serialVersionUID = 1L;
}