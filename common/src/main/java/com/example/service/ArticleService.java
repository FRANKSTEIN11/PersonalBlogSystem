package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Article;
import com.example.vo.ArticleIdAndTitleVO;

import java.util.List;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
public interface ArticleService extends IService<Article> {
    ArticleIdAndTitleVO selectArticleIdAndTitle(ArticleIdAndTitleVO articleIdAndTitleVO);

    List<ArticleIdAndTitleVO> selectHotPoint();

    List<ArticleIdAndTitleVO> selectHeadLine();
}
