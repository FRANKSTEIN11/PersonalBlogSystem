package com.example.blogplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blogplatform.conf.Conf;
import com.example.blogplatform.rabbitmq.producer.ArticleMqService;
import com.example.blogplatform.utils.ArticleUtil;
import com.example.conf.ResultJson;
import com.example.entity.Article;
import com.example.entity.CollectionArticle;
import com.example.entity.Thumbsup;
import com.example.service.ArticleService;
import com.example.service.CollectionService;
import com.example.service.ThumbsupService;
import com.example.vo.ArticleIdAndTitleVO;
import com.example.vo.MqKeyAndValueVO;
import com.sso.ssoCore.helper.CookieStoreBrowserHelper;
import com.sso.ssoCore.helper.SessionAndCookieHelper;
import com.sso.ssoCore.utils.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */

@Slf4j
@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;
    @Resource
    private ThumbsupService thumbsupService;
    @Resource
    private CollectionService collectionService;

    @Resource
    private ArticleMqService articleMqService;


    @RequestMapping("detail")
    public ResultJson detailArticle(HttpServletRequest request, int articleId) {
        Article article = articleService.getById(articleId);
        String articleKey = ArticleUtil.makeArticleKey(articleId);

        //把文章存储到Redis,保持6分钟
        JedisUtil.setex(articleKey, article, Conf.ARTICLE_EXIST_TIME);

        ArticleIdAndTitleVO articleIdAndTitleVO = new ArticleIdAndTitleVO();
        articleIdAndTitleVO.setArticleId(articleId);
        articleIdAndTitleVO.setTitle(article.getTitle());

        //设置LRU热点缓存
        ArticleUtil.getLruCache().put(String.valueOf(articleId), articleIdAndTitleVO);

        //拿到当前登录userId
        String cookieValue = CookieStoreBrowserHelper.getValue(request);
        String userId = SessionAndCookieHelper.parseCookieValueToUserId(cookieValue);
        String viewsKey = ArticleUtil.makeViewsKey(userId, articleId);
        //储存value到redis
        String viewsValue = ArticleUtil.viewsIncr(viewsKey);

        if (viewsValue == null) {
            return ResultJson.ok(article);
        }

        //发送到延迟队列
        log.info("发送到延迟队列: views.ttl.direct.queue");
        MqKeyAndValueVO mqKeyAndValueVO = new MqKeyAndValueVO(viewsKey, viewsValue);
        articleMqService.updateViewsMq(mqKeyAndValueVO);

        return ResultJson.ok(article);
    }

    @RequestMapping("thumbsup")
    public ResultJson thumbsup(HttpServletRequest request, int articleId) {
        String cookieValue = CookieStoreBrowserHelper.getValue(request);
        String userId = SessionAndCookieHelper.parseCookieValueToUserId(cookieValue);
        String thumbsupKey = ArticleUtil.makeThumbsupKey(userId, articleId);


        boolean isThumbsup;
        if (JedisUtil.exists(thumbsupKey)) {
            isThumbsup = false;
        } else {
            isThumbsup = isThumbsup(thumbsupKey);
        }
        //储存value到redis
        String thumbsupValue = ArticleUtil.thumbsupIncr(thumbsupKey, isThumbsup);
        if (thumbsupValue == null) {
            return ResultJson.error();
        }

        //发送到延迟队列
        log.info("发送到延迟队列: " + Conf.THUMBSUP_TTL_DIRECT_QUEUE);
        MqKeyAndValueVO mqKeyAndValueVO = new MqKeyAndValueVO(thumbsupKey, thumbsupValue);
        articleMqService.insertThumbsupMq(mqKeyAndValueVO);

        return ResultJson.ok();
    }

    @RequestMapping("collection")
    public ResultJson collection(HttpServletRequest request, int articleId) {
//        String cookieValue = CookieStoreBrowserHelper.getValue(request);
//        String userId = SessionAndCookieHelper.parseCookieValueToUserId(cookieValue);
        String userId = "1";
        String collectionKey = ArticleUtil.makeCollectionKey(userId, articleId);


        boolean isCollection;
        if (JedisUtil.exists(collectionKey)) {
            isCollection = false;
        } else {
            isCollection = isCollection(collectionKey);
        }
        //储存value到redis
        String collectionValue = ArticleUtil.collectionIncr(collectionKey, isCollection);
        if (collectionValue == null) {
            return ResultJson.error();
        }

        //发送到延迟队列
        log.info("发送到延迟队列: " + Conf.COLLECTION_TTL_DIRECT_QUEUE);
        MqKeyAndValueVO mqKeyAndValueVO = new MqKeyAndValueVO(collectionKey, collectionValue);
        articleMqService.insertCollectionMq(mqKeyAndValueVO);

        return ResultJson.ok();
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
        article.setVisible(Conf.VISIBLE_YES);
        boolean bool = articleService.save(article);

        //把文章存储到Redis,保持6分钟
        String articleKey = ArticleUtil.makeArticleKey(article.getArticleId());
        JedisUtil.setex(articleKey, article, Conf.ARTICLE_EXIST_TIME);

        ArticleIdAndTitleVO articleIdAndTitleVO = new ArticleIdAndTitleVO();
        articleIdAndTitleVO.setArticleId(article.getArticleId());
        articleIdAndTitleVO.setTitle(article.getTitle());

        //设置LRU热点缓存
        ArticleUtil.getLruCache().put(String.valueOf(article.getArticleId()), articleIdAndTitleVO);
        return ResultJson.r(bool);
    }


    /**
     * 去数据库查询是否有点赞记录
     *
     * @param thumbsupKey
     * @return
     */
    public boolean isThumbsup(String thumbsupKey) {
        String articleId = ArticleUtil.parseKeyArticleId(thumbsupKey);
        String userId = ArticleUtil.parseKeyUserId(thumbsupKey);
        QueryWrapper<Thumbsup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        queryWrapper.eq("user_id", userId);
        return thumbsupService.getOne(queryWrapper) != null;
    }


    public boolean isCollection(String collectionKey) {
        String articleId = ArticleUtil.parseKeyArticleId(collectionKey);
        String userId = ArticleUtil.parseKeyUserId(collectionKey);
        QueryWrapper<CollectionArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        queryWrapper.eq("user_id", userId);
        return collectionService.getOne(queryWrapper) != null;
    }
}
