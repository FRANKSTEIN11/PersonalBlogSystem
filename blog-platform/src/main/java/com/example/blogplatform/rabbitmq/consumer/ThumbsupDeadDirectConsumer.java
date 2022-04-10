//package com.example.blogplatform.rabbitmq.consumer;
//
//import com.example.entity.Thumbsup;
//import com.example.vo.MqKeyAndValueVO;
//import com.rabbitmq.client.Channel;
//import com.xkcoding.mq.rabbitmq.test.User;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
///**
// * @author yenanren
// * @date 2022/4/5 0005
// * @Description
// */
//@Service
//@RabbitListener(queues = "thumbsup.dead.direct.queue")
//public class ThumbsupDeadDirectConsumer {
//
//    @RabbitHandler
//    public void receive(MqKeyAndValueVO mqKeyAndValueVO, Message message, Channel channel) {
//
//        System.out.println("wangyi.dead.direct.queue收到的消息dead: " + user);
//        int id = user.getId();
//        if (id == 331) {
//            System.out.println("是redis中最后一次提交");
//        }else{
//            System.out.println("不是   redis中最后一次提交");
//        }
//
//        try {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//        } catch (IOException e) {
//            try {
//                // 处理失败,重新压入MQ
//                channel.basicRecover();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//}
