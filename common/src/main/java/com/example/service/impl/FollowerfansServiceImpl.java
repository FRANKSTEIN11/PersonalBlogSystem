package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Followerfans;
import com.example.mapper.FollowerfansDAO;
import org.springframework.stereotype.Service;
import com.example.service.FollowerfansService;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
@Service
public class FollowerfansServiceImpl extends ServiceImpl<FollowerfansDAO, Followerfans> implements FollowerfansService {
}
