package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
@Data
public class Article implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer articleId;

    private String title;

    private Integer userId;

    private String author;

    private Integer views;

    /**
     * 1是可见,0不可见
     */
    private Integer visible;

    private Date createtime;

    private String content;

    private static final long serialVersionUID = 1L;
}