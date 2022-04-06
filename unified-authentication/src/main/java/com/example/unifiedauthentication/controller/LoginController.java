package com.example.unifiedauthentication.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.conf.ResultJson;
import com.example.entity.Menu;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.mapper.LoginDAO;
import com.example.service.UserService;
import com.example.unifiedauthentication.conf.Conf;
import com.example.unifiedauthentication.utils.CheckBlackListUtil;
import com.sso.ssoCore.entity.SsoUser;
import com.sso.ssoCore.login.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.example.unifiedauthentication.utils.CheckBlackListUtil.loginCountKeyStore;

/**
 * @author yenanren
 * @date 2022/3/7 0007
 * @Description
 */
@Slf4j
@RestController
public class LoginController {
    @Resource
    private UserService userService;
    @Autowired
    private LoginDAO loginDAO;

    @RequestMapping("login")
    public ResultJson login(HttpServletResponse response, User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        User one = userService.getOne(queryWrapper);
        if (one == null) {
            return ResultJson.error("用户名或密码错误!");
        }

        //是否超过登录限制或者已经在黑名单之中
        if (CheckBlackListUtil.isOutLoginLimit(one.getUserId())
                || CheckBlackListUtil.isInBlackList(one.getUserId())) {
            log.warn("用户 " + one.getUserId() + " 超过登录限制,已加入黑名单");
            //设置黑名单
            CheckBlackListUtil.setBlackList(one.getUserId());
            return ResultJson.error(Conf.TIPS_OUT_LOGIN);
        }
        //设置SsoUser的id和name和version和currentTime和Menu
        SsoUser<User> ssoUser = setSsoUser(one);

        log.warn("用户 " + ssoUser.getUserId() + " 存储redis,或者redis++");
        //存储redis,或者key++
        loginCountKeyStore(ssoUser.getUserId());

        log.warn("用户 " + ssoUser.getUserId() + " 开始登录");
        //登录主逻辑
        LoginHelper.login(response, ssoUser, true);

        return ResultJson.ok();
    }

    @RequestMapping("logout")
    public ResultJson logout(HttpServletRequest request, HttpServletResponse response) {
        LoginHelper.logout(request, response);
        return ResultJson.ok();

    }

    @RequestMapping("checkLogin")
    public ResultJson checkLogin(HttpServletRequest request, HttpServletResponse response) {
        boolean bool = LoginHelper.checkLogin(request, response);
        return ResultJson.r(bool);
    }

    /**
     * 设置完整的SsoUser
     *
     * @param user
     * @return
     */
    public SsoUser<User> setSsoUser(User user) {
        SsoUser<User> ssoUser = new SsoUser<>();
        ssoUser.setUserId(user.getUserId());
        ssoUser.setUserName(user.getNickname());
        ssoUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        ssoUser.setCurrentStoreTime(System.currentTimeMillis());

        //设置责任Menu
        setSsoUserMenu(ssoUser);
        return ssoUser;
    }

    /**
     * 设置责任菜单menu
     *
     * @param ssoUser
     */
    public void setSsoUserMenu(SsoUser<User> ssoUser) {
        Role role = loginDAO.selectDuties(Long.valueOf(ssoUser.getUserId()));
        List<Menu> menus = loginDAO.selectMenu(Long.valueOf(role.getRoleId()));
        Set<String> menuAuth = new HashSet<>();
        Set<String> menuTree = new HashSet<>();
        for (Menu menu : menus) {
            menuAuth.add(menu.getMenuAuth());
            menuTree.add(menu.getMenuUrl());
        }
        ssoUser.setMenuAuth(menuAuth);
        ssoUser.setMenuTree(menuTree);

    }


}
