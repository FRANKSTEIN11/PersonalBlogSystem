package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.RefUserRole;
import com.example.mapper.RefUserRoleDAO;
import org.springframework.stereotype.Service;
import com.example.service.RefUserRoleService;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
@Service
public class RefUserRoleServiceImpl extends ServiceImpl<RefUserRoleDAO, RefUserRole> implements RefUserRoleService {
}
