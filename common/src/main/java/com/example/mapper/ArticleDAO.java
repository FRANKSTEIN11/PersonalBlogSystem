package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Article;
import com.example.vo.ArticleIdAndTitleVO;
import com.example.vo.ViewsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
public interface ArticleDAO extends BaseMapper<Article> {
    ArticleIdAndTitleVO selectArticleIdAndTitle(@Param("articleIdAndTitleVO") ArticleIdAndTitleVO articleIdAndTitleVO);

    List<ArticleIdAndTitleVO> selectHotPoint();

    List<ArticleIdAndTitleVO> selectHeadLine();

    void updateViews(ViewsVO viewsVO);

}
