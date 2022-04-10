package com.example.blogplatform.rabbitmq.producer;

import com.example.vo.MqKeyAndValueVO;

/**
 * @author yenanren
 * @date 2022/4/10 0010
 * @Description
 */
public interface ArticleMqService {
    void updateViewsMq(MqKeyAndValueVO mqKeyAndValueVO);

    void insertThumbsupMq(MqKeyAndValueVO mqKeyAndValueVO);
}
