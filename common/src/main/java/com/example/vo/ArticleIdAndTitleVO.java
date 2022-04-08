package com.example.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author yenanren
 * @date 2022/4/8 0008
 * @Description
 */
@Data
public class ArticleIdAndTitleVO {
    @TableId(type = IdType.AUTO)
    private Integer articleId;

    private String title;
}
