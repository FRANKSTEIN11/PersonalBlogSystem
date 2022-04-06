package com.example.unifiedauthentication.utils;

import com.example.unifiedauthentication.conf.Conf;
import com.sso.ssoCore.utils.JedisUtil;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
public class CheckBlackListUtil {


    public static void loginCountKeyStore(int id) {
        String loginCountKey = makeLoginCountKey(id);

        //是否存在loginCountKey,如果存在了,就说明登录过了,value加1,如果不存在就设置60s的key
        if (JedisUtil.exists(loginCountKey)) {
            JedisUtil.incrBy(loginCountKey);
            JedisUtil.expire(loginCountKey, Conf.TIME_INIT);
        } else {
            JedisUtil.setex(loginCountKey, Conf.COUNT_INIT, Conf.TIME_INIT);
        }
    }

    public static boolean isOutLoginLimit(int id) {
        String loginCountKey = makeLoginCountKey(id);
        boolean bool = false;
        if (JedisUtil.exists(loginCountKey)) {
            bool = Integer.parseInt(JedisUtil.getStringValue(loginCountKey)) > 5;
        }
        return bool;
    }

//---------------------------------------------------------------------------BlackList系列
    /**
     * 制作黑名单key
     *
     * @param id
     * @return
     */
    public static String makeBlackListKey(int id) {
        return Conf.BLACKLIST_LOGIN.concat(Conf.BLACKLIST_SYMBOL).concat(String.valueOf(id));
    }

    public static String makeBlackListKey(String id) {
        return Conf.BLACKLIST_LOGIN.concat(Conf.BLACKLIST_SYMBOL).concat(id);
    }

    public static boolean isInBlackList(int id) {
        String blackListKey = makeBlackListKey(id);
        return JedisUtil.exists(blackListKey);
    }

    public static boolean isInBlackList(String id) {
        String blackListKey = makeBlackListKey(id);
        return JedisUtil.exists(blackListKey);
    }

    public static void setBlackList(int id) {
        //如果登录次数超过5,直接加入黑名单
        String blackListKey = makeBlackListKey(id);
        JedisUtil.setex(blackListKey, "1", Conf.TIME_BLACKLIST);
    }

    public static void setBlackList(String id) {
        //如果登录次数超过5,直接加入黑名单
        String blackListKey = makeBlackListKey(id);
        JedisUtil.setex(blackListKey, "1", Conf.TIME_BLACKLIST);
    }
//----------------------------------------------------------------------------------------

    /**
     * 制作loginCountKey
     *
     * @param id
     * @return
     */
    public static String makeLoginCountKey(int id) {
        return Conf.LOGIN_COUNT.concat(Conf.COUNT_SYMBOL).concat(String.valueOf(id));
    }

    public static String makeLoginCountKey(String id) {
        return Conf.LOGIN_COUNT.concat(Conf.COUNT_SYMBOL).concat(id);
    }

}
