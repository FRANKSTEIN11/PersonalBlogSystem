package com.example.blogplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blogplatform.conf.Conf;
import com.example.blogplatform.utils.ArticleUtil;
import com.example.conf.ResultJson;
import com.example.entity.Article;
import com.example.service.ArticleService;
import com.example.vo.ArticleIdAndTitleVO;
import com.example.vo.ViewsVO;
import com.sso.ssoCore.utils.JedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("detail")
    public ResultJson detailArticle(HttpServletRequest request, int articleId) {
        Article article = articleService.getById(articleId);
        String str = ArticleUtil.makeArticleKey(articleId);

        //把文章存储到Redis,保持6分钟
        JedisUtil.setex(str, article, Conf.ARTICLE_EXIST_TIME);

        ArticleIdAndTitleVO articleIdAndTitleVO = new ArticleIdAndTitleVO();
        articleIdAndTitleVO.setArticleId(articleId);
        articleIdAndTitleVO.setTitle(article.getTitle());

        //设置LRU热点缓存
        ArticleUtil.getLruCache().put(String.valueOf(articleId), articleIdAndTitleVO);

        //储存到views++到redis
        String value = ArticleUtil.viewsIncr(request, articleId);

        if (value == null) {
            return ResultJson.ok(article);
        }

        //发送到延迟队列


        return ResultJson.ok(article);
    }

    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : Conf.HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
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
        article.setVisible(1);
        boolean bool = articleService.save(article);

        //把文章存储到Redis,保持6分钟
        String str = ArticleUtil.makeArticleKey(article.getArticleId());
        JedisUtil.setex(str, article, Conf.ARTICLE_EXIST_TIME);

        ArticleIdAndTitleVO articleIdAndTitleVO = new ArticleIdAndTitleVO();
        articleIdAndTitleVO.setArticleId(article.getArticleId());
        articleIdAndTitleVO.setTitle(article.getTitle());

        //设置LRU热点缓存
        ArticleUtil.getLruCache().put(String.valueOf(article.getArticleId()), articleIdAndTitleVO);

        return ResultJson.r(bool);
    }

}
