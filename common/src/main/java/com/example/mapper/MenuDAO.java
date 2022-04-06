package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Article;
import com.example.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * MenuDAO继承基类
 */
@Mapper
@Repository
public interface MenuDAO extends BaseMapper<Menu> {
}