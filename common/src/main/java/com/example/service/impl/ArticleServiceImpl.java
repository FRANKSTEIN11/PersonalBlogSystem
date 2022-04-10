package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Article;
import com.example.mapper.ArticleDAO;
import com.example.service.ArticleService;
import com.example.vo.ArticleIdAndTitleVO;
import com.example.vo.ViewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDAO, Article> implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public ArticleIdAndTitleVO selectArticleIdAndTitle(ArticleIdAndTitleVO articleIdAndTitleVO) {
        return articleDAO.selectArticleIdAndTitle(articleIdAndTitleVO);
    }

    @Override
    public List<ArticleIdAndTitleVO> selectHotPoint() {
        return articleDAO.selectHotPoint();
    }

    @Override
    public List<ArticleIdAndTitleVO> selectHeadLine() {
        return articleDAO.selectHeadLine();
    }

    @Override
    @Transactional
    public void updateViews(ViewsVO viewsVO) {
        articleDAO.updateViews(viewsVO);
    }


}
