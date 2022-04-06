package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Article;
import com.example.entity.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * CommentsDAO继承基类
 */
@Mapper
@Repository
public interface CommentsDAO extends BaseMapper<Comments> {
}