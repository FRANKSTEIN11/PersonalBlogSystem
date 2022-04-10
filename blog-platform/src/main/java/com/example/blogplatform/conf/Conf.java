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

    /**
     * 存储到Redis的连接符
     */
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
     * 默认可见
     */
    public static final int VISIBLE_YES = 1;

    /**
     * 默认不可见
     */
    public static final int VISIBLE_NO = 0;


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

//----------------------------------------------------------------------------------->rabbitMQ

    public static final String VIEWS_ROUTING_KEY = "views";
    public static final String THUMBSUP_ROUTING_KEY = "thumbsup";
    public static final String COLLECTION_ROUTING_KEY = "collection";
    public static final String TTL_DIRECT_EXCHANGE = "ttl_direct_exchange";
    public static final String VIEWS_TTL_DIRECT_QUEUE = "views.ttl.direct.queue";
    public static final String THUMBSUP_TTL_DIRECT_QUEUE = "thumbsup.ttl.direct.queue";
    public static final String COLLECTION_TTL_DIRECT_QUEUE = "collection.ttl.direct.queue";
    public static final String DEAD_DIRECT_EXCHANGE = "dead_direct_exchange";
    public static final String VIEWS_DEAD_DIRECT_QUEUE = "views.dead.direct.queue";
    public static final String THUMBSUP_DEAD_DIRECT_QUEUE = "thumbsup.dead.direct.queue";
    public static final String COLLECTION_DEAD_DIRECT_QUEUE = "collection.dead.direct.queue";

//--------------------------------------------------------------------------------------------


}
