package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.CollectionArticle;
import com.example.mapper.CollectionDAO;
import org.springframework.stereotype.Service;
import com.example.service.CollectionService;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionDAO, CollectionArticle> implements CollectionService {
}
