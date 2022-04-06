package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.RefRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * RefRoleMenuDAO继承基类
 */
@Mapper
@Repository
public interface RefRoleMenuDAO extends BaseMapper<RefRoleMenu> {

}