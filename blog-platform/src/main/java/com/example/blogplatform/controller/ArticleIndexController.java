package com.example.blogplatform.controller;

import cn.hutool.cache.impl.LRUCache;
import com.example.blogplatform.utils.ArticleUtil;
import com.example.conf.ResultJson;
import com.example.service.ArticleService;
import com.example.service.UserService;
import com.example.vo.ArticleIdAndTitleVO;
import com.example.vo.UserIdAndNameVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yenanren
 * @date 2022/4/8 0008
 * @Description
 */

@RestController
@RequestMapping("articleIndex")
public class ArticleIndexController {

    @Resource
    private ArticleService articleService;

    @Resource
    private UserService userService;

    /**
     * 一天内阅读量最高
     *
     * @return
     */
    @RequestMapping("hostPointArticle")
    public ResultJson hostPointArticle() {
        List<ArticleIdAndTitleVO> articleIdAndTitleVOS = articleService.selectHotPoint();
        return ResultJson.ok(articleIdAndTitleVOS);
    }

    /**
     * LRU算法
     */
    @RequestMapping("recommandArticle")
    public ResultJson recommandArticle() {

        //从lruCache中获取
        LRUCache<String, ArticleIdAndTitleVO> lruCache = ArticleUtil.getLruCache();
        List<ArticleIdAndTitleVO> list = new ArrayList<>();
        for (ArticleIdAndTitleVO articleIdAndTitleVO : lruCache) {
            list.add(articleIdAndTitleVO);
        }
        return ResultJson.ok(list);
    }

    /**
     * 头条
     *
     * @return
     */
    @RequestMapping("headLine")
    public ResultJson headLine() {

        List<ArticleIdAndTitleVO> articleIdAndTitleVOS = articleService.selectHotPoint();
        return ResultJson.ok(articleIdAndTitleVOS);
    }

    /**
     * 黑名单
     *
     * @return
     */
    @RequestMapping("blackList")
    public ResultJson blackList() {

        List<UserIdAndNameVO> userIdAndNameVOS = userService.selectBlackList();
        return ResultJson.ok(userIdAndNameVOS);

    }

}
