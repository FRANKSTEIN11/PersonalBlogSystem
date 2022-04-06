package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Thumbsup;
import com.example.mapper.ThumbsupDAO;
import org.springframework.stereotype.Service;
import com.example.service.ThumbsupService;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
@Service
public class ThumbsupServiceImpl extends ServiceImpl<ThumbsupDAO, Thumbsup> implements ThumbsupService {
}
