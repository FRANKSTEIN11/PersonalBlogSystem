package com.example.blogplatform.conf;

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

    /**
     * 存储到Redis的views的key
     */
    public static final String VIEWS_KEY = "views";

    /**
     * 存储到Redis的thumbup的key
     */
    public static final String THUMBSUP_KEY = "thumbsup";

    /**
     * 存储到Redis的collection的key
     */
    public static final String COLLECTION_KEY = "collection";

    /**
     * 默认初始数量
     */
    public static final int DEFAULT_INIT = 1;

    /**
     * 默认最大数量
     */
    public static final int DEFAULT_MAX = 5;


    /**
     * Http头的key,用来获取客户端IP
     */
    public static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "X-Real-IP"
    };
}
