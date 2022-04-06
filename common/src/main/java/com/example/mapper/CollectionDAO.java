package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.CollectionArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * CollectionDAO继承基类
 */
@Mapper
@Repository
public interface CollectionDAO extends BaseMapper<CollectionArticle> {
}