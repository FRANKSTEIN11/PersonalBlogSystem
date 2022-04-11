package com.example.blogplatform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blogplatform.web.PageWeb;
import com.example.conf.ResultJson;
import com.example.entity.*;
import com.example.service.*;
import com.example.vo.UserIdAndNameVO;
import com.sso.ssoCore.entity.SsoUser;
import com.sso.ssoCore.helper.SessionStoreRedisHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 统一userIndex,前端判断是否自己的userZone就可以
 *
 * @author yenanren
 * @date 2022/4/10 0010
 * @Description
 */

@RestController
@RequestMapping("indexUser")
public class IndexUserController {

    @Resource
    private UserService userService;
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

    @RequestMapping("userDetail")
    public ResultJson userDetail(String userId) {
        SsoUser ssoUser = SessionStoreRedisHelper.get(Integer.valueOf(userId));
        if (ssoUser != null) {
            return ResultJson.ok(ssoUser);
        }
        UserIdAndNameVO userIdAndNameVO = userService.selectUserDetail(userId);
        return ResultJson.ok(userIdAndNameVO);
    }

    @RequestMapping("selfCollection")
    public ResultJson selfCollection(String userId, long pageNum, long pageSize) {
        //从cookies中获取userId,和前端传来的id进行对比
        PageWeb<CollectionArticle> pageWeb = new PageWeb<>();
        Page<CollectionArticle> page;
        if (pageNum == 0 || pageSize == 0) {
            page = new Page<>(pageWeb.getPageNum(), pageWeb.getPageSize());
        }
        page = new Page<>(pageNum, pageSize);

        QueryWrapper<CollectionArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        IPage<CollectionArticle> iPage = collectionService.page(page, queryWrapper);
        return ResultJson.ok(PageWeb.bulidPageWeb(iPage));

    }

    @RequestMapping("article")
    public ResultJson article(String userId, long pageNum, long pageSize) {
        PageWeb<Article> pageWeb = new PageWeb<>();
        Page<Article> page;
        if (pageNum == 0 || pageSize == 0) {
            page = new Page<>(pageWeb.getPageNum(), pageWeb.getPageSize());
        }
        page = new Page<>(pageNum, pageSize);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        IPage<Article> iPage = articleService.page(page, queryWrapper);
        return ResultJson.ok(PageWeb.bulidPageWeb(iPage));
    }

    @RequestMapping("thumbsup")
    public ResultJson thumbsup(String userId) {
        QueryWrapper<Thumbsup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        int count = thumbsupService.count(queryWrapper);
        return ResultJson.ok(count);
    }

    @RequestMapping("comments")
    public ResultJson comments(String userId) {
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        int count = commentsService.count(queryWrapper);
        return ResultJson.ok(count);
    }

    @RequestMapping("collection")
    public ResultJson collection(String userId) {
        QueryWrapper<CollectionArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        int count = collectionService.count(queryWrapper);
        return ResultJson.ok(count);
    }

    @RequestMapping("follower")
    public ResultJson follower(String userId) {
        QueryWrapper<Followerfans> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fans_id", userId);
        int count = followerfansService.count(queryWrapper);
        return ResultJson.ok(count);
    }

    @RequestMapping("fans")
    public ResultJson fans(String userId) {
        QueryWrapper<Followerfans> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("follower_id", userId);
        int count = followerfansService.count(queryWrapper);
        return ResultJson.ok(count);
    }


}
