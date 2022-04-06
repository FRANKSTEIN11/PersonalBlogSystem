package com.example.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class CollectionArticle implements Serializable {
    private Integer articleId;

    private Integer userId;

    private static final long serialVersionUID = 1L;
}