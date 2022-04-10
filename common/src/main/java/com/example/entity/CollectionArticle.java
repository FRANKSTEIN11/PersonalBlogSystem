package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author
 */
@Data
public class CollectionArticle implements Serializable {
    private Integer articleId;

    private Integer userId;


    @TableField(exist = false)
    private String uuid;

    private static final long serialVersionUID = 1L;
}