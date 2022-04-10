package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.User;
import com.example.vo.UserIdAndNameVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDAO继承基类
 */
@Mapper
@Repository
public interface UserDAO extends BaseMapper<User> {
    List<UserIdAndNameVO> selectBlackList();

    UserIdAndNameVO selectUserDetail(@Param("userId") String userId);
}