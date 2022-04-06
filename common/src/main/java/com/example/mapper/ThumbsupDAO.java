package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Article;
import com.example.entity.Thumbsup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ThumbsupDAO继承基类
 */
@Mapper
@Repository
public interface ThumbsupDAO extends BaseMapper<Thumbsup> {
}