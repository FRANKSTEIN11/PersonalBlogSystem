package com.example.blogplatform.utils;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import com.example.blogplatform.conf.Conf;
import com.example.vo.ArticleIdAndTitleVO;

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
}
