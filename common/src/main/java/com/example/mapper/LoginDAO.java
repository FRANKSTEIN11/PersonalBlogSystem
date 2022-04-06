package com.example.mapper;

import com.example.entity.Menu;
import com.example.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
@Mapper
@Repository
public interface LoginDAO {

    Role selectDuties(@Param("id") Long id);

    List<Menu> selectMenu(@Param("id") Long id);
}
