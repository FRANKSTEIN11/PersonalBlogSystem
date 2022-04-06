package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Article;
import com.example.entity.RefUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * RefUserRoleDAO继承基类
 */
@Mapper
@Repository
public interface RefUserRoleDAO extends BaseMapper<RefUserRole> {

}