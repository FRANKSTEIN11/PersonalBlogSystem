package com.example.blogarticleplatform.conf;

/**
 * @author yenanren
 * @date 2022/4/8 0008
 * @Description
 */
public class Conf {
    /**
     * 存储到Redis的article的key
     */
    public static final String ARTICLE_KEY = "articleId";

    public static final String ARTICLE_SYMBOL = ":";

    /**
     * 存储到Redis的article的时间为6分钟
     */
    public static final long ARTICLE_EXIST_TIME = 360;


}
