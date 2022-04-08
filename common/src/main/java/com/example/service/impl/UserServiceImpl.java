package com.example.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.User;
import com.example.mapper.UserDAO;
import com.example.service.UserService;
import com.example.vo.UserIdAndNameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDAO, User> implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<UserIdAndNameVO> selectBlackList() {
        return userDAO.selectBlackList();
    }

}
