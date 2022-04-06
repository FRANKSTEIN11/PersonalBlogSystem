package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Article;
import com.example.entity.Followerfans;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * FollowerfansDAO继承基类
 */
@Mapper
@Repository
public interface FollowerfansDAO extends BaseMapper<Followerfans> {
}