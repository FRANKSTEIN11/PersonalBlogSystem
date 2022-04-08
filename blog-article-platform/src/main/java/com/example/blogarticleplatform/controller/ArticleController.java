package com.example.blogarticleplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blogarticleplatform.conf.Conf;
import com.example.blogarticleplatform.utils.ArticleUtil;
import com.example.conf.ResultJson;
import com.example.entity.Article;
import com.example.service.*;
import com.example.vo.ArticleIdAndTitleVO;
import com.sso.ssoCore.utils.JedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */

@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;
    @Resource
    private CollectionService collectionService;
    @Resource
    private CommentsService commentsService;
    @Resource
    private FollowerfansService followerfansService;
    @Resource
    private ThumbsupService thumbsupService;

    @RequestMapping("detail")
    public ResultJson detailArticle(int id) {
        Article article = articleService.getById(id);
        String str = ArticleUtil.makeArticleKey(id);

        //把文章存储到Redis,保持6分钟
        JedisUtil.setex(str, article, Conf.ARTICLE_EXIST_TIME);

        ArticleIdAndTitleVO articleIdAndTitleVO = new ArticleIdAndTitleVO();
        articleIdAndTitleVO.setArticleId(id);
        articleIdAndTitleVO.setTitle(article.getTitle());

        //设置LRU热点缓存
        ArticleUtil.lruCache.put(String.valueOf(id), articleIdAndTitleVO);
        return ResultJson.ok(article);
    }


    @RequestMapping("update")
    public ResultJson updateArticle(Article article) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", article.getArticleId());
        boolean bool = articleService.update(article, queryWrapper);
        return ResultJson.r(bool);
    }

    @RequestMapping("delete")
    public ResultJson deleteArticle(Article article) {
        boolean bool = articleService.removeById(article.getArticleId());
        return ResultJson.r(bool);
    }

    @RequestMapping("insert")
    public ResultJson insertArticle(Article article) {
        boolean bool = articleService.save(article);

        String str = ArticleUtil.makeArticleKey(article.getArticleId());
        //把文章存储到Redis,保持6分钟
        JedisUtil.setex(str, article, Conf.ARTICLE_EXIST_TIME);

        ArticleIdAndTitleVO articleIdAndTitleVO = new ArticleIdAndTitleVO();
        articleIdAndTitleVO.setArticleId(article.getArticleId());
        articleIdAndTitleVO.setTitle(article.getTitle());

        //设置LRU热点缓存
        ArticleUtil.lruCache.put(String.valueOf(article.getArticleId()), articleIdAndTitleVO);

        return ResultJson.r(bool);
    }


}
