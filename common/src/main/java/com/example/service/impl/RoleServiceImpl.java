package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Role;
import com.example.mapper.RoleDAO;
import org.springframework.stereotype.Service;
import com.example.service.RoleService;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDAO, Role> implements RoleService {
}
