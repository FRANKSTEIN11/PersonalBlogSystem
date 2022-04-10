package com.example.blogplatform.utils;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import com.example.blogplatform.conf.Conf;
import com.example.vo.ArticleIdAndTitleVO;
import com.sso.ssoCore.helper.CookieStoreBrowserHelper;
import com.sso.ssoCore.helper.SessionAndCookieHelper;
import com.sso.ssoCore.utils.JedisUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author yenanren
 * @date 2022/4/8 0008
 * @Description
 */
public class ArticleUtil {

    public static String makeArticleKey(int id) {
        String articleKey = Conf.ARTICLE_KEY.concat(Conf.ARTICLE_SYMBOL).concat(String.valueOf(id));
        return articleKey;
    }

    public static LRUCache<String, ArticleIdAndTitleVO> lruCache = CacheUtil.newLRUCache(7);

    public static LRUCache<String, ArticleIdAndTitleVO> getLruCache() {
        return lruCache;
    }

    /**
     * views增加,通过延迟队列的限制xx秒(得到之后删除redis中的key),与该方法拿出count和默认限制刷新此处进行大小的判断,以此来限制
     *
     * @param request
     * @param articleId
     * @return
     */
    public static String viewsIncr(HttpServletRequest request, Integer articleId) {
        //获取userId
        String cookieValue = CookieStoreBrowserHelper.getValue(request);
        String userId = SessionAndCookieHelper.parseCookieValueToUserId(cookieValue);

        //制作viewsKey
        String viewsKey = makeViewsKey(userId, articleId);
        if (JedisUtil.exists(viewsKey)) {

            //得到value值
            String value = JedisUtil.getStringValue(viewsKey);
            Integer count = parseValueCount(value);
            if (count > Conf.DEFAULT_MAX) {
                return null;
            }
            //重新设置value的count值;
            count++;
            value = makeViewsValue(count);

            //储存到redis
            JedisUtil.set(viewsKey, value);
            return value;
        } else {
            String value = makeViewsValue(Conf.DEFAULT_INIT);
            JedisUtil.set(viewsKey, value);
            return value;
        }
    }

    /**
     * 生成的key为:  views:articleId:userId
     *
     * @param userId
     * @param articleId
     * @return
     */
    public static String makeViewsKey(String userId, Integer articleId) {
        String viewsKey = Conf.VIEWS_KEY
                .concat(Conf.ARTICLE_SYMBOL)
                .concat(String.valueOf(articleId))
                .concat(Conf.ARTICLE_SYMBOL)
                .concat(userId);
        return viewsKey;
    }

    /**
     * 生成的value为:  count:uuid
     *
     * @param count
     * @return
     */
    public static String makeViewsValue(int count) {
        String value = String.valueOf(count)
                .concat(Conf.ARTICLE_SYMBOL)
                .concat(UUID.randomUUID().toString().replaceAll("-", ""));
        return value;
    }

//--------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------->解析字符串

    /**
     * 得到value中的count值
     *
     * @param str
     * @return
     */
    public static Integer parseValueCount(String str) {
        if (str != null && str.indexOf(Conf.ARTICLE_SYMBOL) > -1) {
            String[] values = str.split(Conf.ARTICLE_SYMBOL);
            if (values[0] != null && values[0].length() > 0) {
                return Integer.valueOf(values[0]);
            }
        }
        return null;
    }

    public static String parseValueUuid(String str) {
        if (str != null && str.indexOf(Conf.ARTICLE_SYMBOL) > -1) {
            String[] values = str.split(Conf.ARTICLE_SYMBOL);
            if (values[1] != null && values[1].length() > 0) {
                return values[1];
            }
        }
        return null;
    }

    public static String parseKeyArticleId(String str) {
        if (str != null && str.indexOf(Conf.ARTICLE_SYMBOL) > -1) {
            String[] keys = str.split(Conf.ARTICLE_SYMBOL);
            if (keys[1] != null && keys[1].length() > 0) {
                return keys[1];
            }
        }
        return null;
    }

    public static String parseKeyUserId(String str) {
        if (str != null && str.indexOf(Conf.ARTICLE_SYMBOL) > -1) {
            String[] keys = str.split(Conf.ARTICLE_SYMBOL);
            if (keys[2] != null && keys[2].length() > 0) {
                return keys[2];
            }
        }
        return null;
    }

}
