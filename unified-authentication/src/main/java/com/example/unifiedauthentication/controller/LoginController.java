package com.example.unifiedauthentication.controller;

import com.example.unifiedauthentication.conf.Conf;
import com.example.unifiedauthentication.conf.ResultJson;
import com.example.unifiedauthentication.utils.CheckBlackListUtil;
import com.sso.ssoCore.entity.SsoUser;
import com.sso.ssoCore.login.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("login")
    public ResultJson login(HttpServletResponse response) {
        SsoUser ssoUser = new SsoUser<>();
        ssoUser.setUserId(12345);
        ssoUser.setUserName("老王");
        ssoUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        ssoUser.setCurrentStoreTime(System.currentTimeMillis());

        //是否超过登录限制或者已经在黑名单之中
        if (CheckBlackListUtil.isOutLoginLimit(ssoUser.getUserId())
                || CheckBlackListUtil.isInBlackList(ssoUser.getUserId())) {
            log.warn("用户 " + ssoUser.getUserId() + " 超过登录限制,已加入黑名单");
            //设置黑名单
            CheckBlackListUtil.setBlackList(ssoUser.getUserId());
            return ResultJson.error(Conf.TIPS_OUT_LOGIN);
        }
        log.warn("用户 " + ssoUser.getUserId() + " 存储redis,或者redis++");
        //存储redis,或者key++
        loginCountKeyStore(ssoUser.getUserId());

        log.warn("用户 " + ssoUser.getUserId() + " 开始登录");
        //登录主逻辑
        LoginHelper.login(response, ssoUser, true);

        return ResultJson.ok();
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        LoginHelper.logout(request, response);
        System.out.println("登出成功");
        return "登录成功";

    }

    @RequestMapping("checkLogin")
    public String checkLogin(HttpServletRequest request, HttpServletResponse response) {
        boolean b = LoginHelper.checkLogin(request, response);
        System.out.println("检测登录: " + b);
        return "检测登录: " + b;

    }

}
