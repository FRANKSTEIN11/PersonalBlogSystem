package com.example.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yenanren
 * @date 2022/4/8 0008
 * @Description
 */
@Data
public class ArticleIdAndTitleVO implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer articleId;

    private String title;

    private static final long serialVersionUID = 1L;
}
