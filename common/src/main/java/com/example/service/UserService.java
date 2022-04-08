package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.User;
import com.example.vo.UserIdAndNameVO;

import java.util.List;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
public interface UserService extends IService<User> {
    List<UserIdAndNameVO> selectBlackList( );
}
