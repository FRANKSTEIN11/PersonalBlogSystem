package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Article;
import com.example.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * RoleDAO继承基类
 */
@Mapper
@Repository
public interface RoleDAO extends BaseMapper<Role> {
}