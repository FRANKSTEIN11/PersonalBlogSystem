package com.example.unifiedauthentication.controller;

import com.example.annonation.CheckWord;
import com.example.conf.ResultJson;
import com.example.entity.RefUserRole;
import com.example.entity.User;
import com.example.service.RefUserRoleService;
import com.example.service.UserService;
import com.example.unifiedauthentication.conf.Conf;
import com.example.unifiedauthentication.utils.CheckBlackListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
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
@RequestMapping("register")
public class RegisterController {

    @Resource
    private UserService userService;
    @Resource
    private RefUserRoleService refUserRoleService;

    @CheckWord
    @RequestMapping
    public ResultJson register(HttpServletRequest request, User user) {
        //获取客户端IP
        String clientIpAddress = getClientIpAddress(request);
        if (CheckBlackListUtil.isInBlackList(clientIpAddress)) {
            log.warn("用户: " + clientIpAddress + "   " + Conf.TIPS_OUT_REGISTER);
            return ResultJson.error(Conf.TIPS_OUT_REGISTER);
        }

        if (StringUtils.hasLength(user.getUsername())
                || StringUtils.hasLength(user.getPassword())
                || StringUtils.hasLength(user.getNickname())) {

            //储存到数据库
            userService.save(user);
            //设置角色Role关联
            setRefUserRole(user);

            //设置黑名单,拒绝再次注册,一小时之后才可以注册
            CheckBlackListUtil.setBlackList(clientIpAddress);

            return ResultJson.ok();
        }
        return ResultJson.error();
    }


    /**
     * 设置角色Role关联
     *
     * @param user
     */
    public void setRefUserRole(User user) {
        RefUserRole refUserRole = new RefUserRole();
        refUserRole.setRoleId(Conf.NORMAL_ROLE_ID);
        refUserRole.setUserId(user.getUserId());
        refUserRoleService.save(refUserRole);
    }

    /***
     * 获取客户端ip地址(可以穿透代理)
     * @param request
     * @return
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : Conf.HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
}
