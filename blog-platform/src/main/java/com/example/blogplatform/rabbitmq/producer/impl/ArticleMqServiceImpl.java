package com.example.blogplatform.rabbitmq.producer.impl;

import com.example.blogplatform.conf.Conf;
import com.example.blogplatform.rabbitmq.producer.ArticleMqService;
import com.example.vo.MqKeyAndValueVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yenanren
 * @date 2022/4/10 0010
 * @Description
 */
@Service
public class ArticleMqServiceImpl implements ArticleMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void updateViewsMq(MqKeyAndValueVO mqKeyAndValueVO) {

        rabbitTemplate.convertAndSend(Conf.TTL_DIRECT_EXCHANGE, Conf.VIEWS_ROUTING_KEY, mqKeyAndValueVO);
    }

    @Override
    public void insertThumbsupMq(MqKeyAndValueVO mqKeyAndValueVO) {

        rabbitTemplate.convertAndSend(Conf.TTL_DIRECT_EXCHANGE, Conf.THUMBSUP_ROUTING_KEY, mqKeyAndValueVO);
    }

    @Override
    public void insertCollectionMq(MqKeyAndValueVO mqKeyAndValueVO) {

        rabbitTemplate.convertAndSend(Conf.TTL_DIRECT_EXCHANGE, Conf.COLLECTION_ROUTING_KEY, mqKeyAndValueVO);
    }
}
