package com.example.unifiedauthentication.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具
 */
@Slf4j
public class RegexUtil {
    /**
     * 验证是否是四位数字
     *
     * @param code
     * @return
     */
    public static boolean isFourNumber(String code) {
        Pattern p = Pattern.compile("\\d{4}");
        Matcher m = p.matcher(code);
        log.info(m.matches() + "---");
        return m.matches();
    }

    /**
     * 验证是否为六位数字
     *
     * @param code
     * @return
     */
    public static boolean isSixNumber(String code) {
        Pattern p = Pattern.compile("\\d{6}");
        Matcher m = p.matcher(code);
        log.info(m.matches() + "---");
        return m.matches();
    }

    /**
     * 验证是否为手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        log.info(m.matches() + "---");
        return m.matches();
    }

    /**
     * 验证是否为邮箱验证码
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        log.info(m.matches() + "---");
        return m.matches();
    }


}
