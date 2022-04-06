package com.example.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class Comments implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer auconnectionId;

    private Integer articleId;

    private Integer userId;

    private String comment;

    /**
     * 回复的文章auconnectionid

     */
    private Integer version;

    private static final long serialVersionUID = 1L;
}