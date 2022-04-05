package com.example.unifiedauthentication.conf;

/**
 * @author yenanren
 * @date 2022/4/5 0005
 * @Description
 */
public class Conf {
    /**
     * 登录计数
     */
    public static final String LOGIN_COUNT = "login_count";
    /**
     * 注册计数
     */
    public static final String REGISTER_COUNT = "register_count";
    /**
     * count连接符
     */
    public static final String COUNT_SYMBOL = "=";
    /**
     * count初始大小
     */
    public static final String COUNT_INIT = "1";
    /**
     * 存活时间初始大小
     */
    public static final long TIME_INIT = 60;
    /**
     * 黑名单名称
     */
    public static final String BLACKLIST_LOGIN = "blackList_login";
    /**
     * 黑名单象征
     */
    public static final String BLACKLIST_SYMBOL = ":";
    /**
     * 黑名单存活时间
     */
    public static final long TIME_BLACKLIST = 3600;
    /**
     * 超过登录限制提示
     */
    public static final String TIPS_OUT_LOGIN = "超过登录限制,请1小时之后再试!";


}
