package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Menu;
import com.example.mapper.MenuDAO;
import org.springframework.stereotype.Service;
import com.example.service.MenuService;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDAO, Menu> implements MenuService {
}
