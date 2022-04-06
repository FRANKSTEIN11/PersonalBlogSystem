package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Comments;
import com.example.mapper.CommentsDAO;
import org.springframework.stereotype.Service;
import com.example.service.CommentsService;

/**
 * @author yenanren
 * @date 2022/4/6 0006
 * @Description
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsDAO, Comments> implements CommentsService {
}
