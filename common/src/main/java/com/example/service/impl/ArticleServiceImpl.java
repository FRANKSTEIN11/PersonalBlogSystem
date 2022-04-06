package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Article;
import com.example.mapper.ArticleDAO;
import com.example.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDAO, Article> implements ArticleService {
}
