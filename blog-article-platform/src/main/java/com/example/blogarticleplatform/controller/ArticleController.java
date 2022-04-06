package com.example.blogarticleplatform.controller;

import com.example.conf.ResultJson;
import com.example.entity.Article;
import com.example.service.*;
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
    public ResultJson detail(int id) {
        Article article = articleService.getById(id);
        return ResultJson.ok(article);
    }

}
