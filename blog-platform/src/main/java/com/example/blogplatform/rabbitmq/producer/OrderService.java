//package com.example.blogplatform.rabbitmq.producer;
//
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @author yenanren
// * @date 2022/4/5 0005
// * @Description
// */
//
//@Service
//public class OrderService {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void makeOrder() {
//        //根据商品id查询库存是否充足
//
//        //保存订单
//        //通过MQ来完成消息的分发
//        String exchangeName = "fanout_order_exchange";
//        String routingKey = "";
//
//        User user = new User();
//        user.setId(33);
//        user.setName("王毅");
//        rabbitTemplate.convertAndSend(exchangeName, routingKey, user);
//    }
//
//    public void makeTTLOrder() {
//        String exchangeName = "ttl_direct_exchange";
//        String routingKey = "ttl";
//
//        User user = new User();
//        user.setId(331);
//        user.setName("王毅");
//
//        try {
//            rabbitTemplate.convertAndSend(exchangeName, routingKey, user);
//        } catch (AmqpException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void makeTTLOrder(int num) {
//
//    }
//
//    public void emailTTL() {
//        String exchangeName = "ttl_direct_exchange";
//        String routingKey = "email";
//
//        User user = new User();
//        user.setId(33);
//        user.setName("  emailTTL的  ");
//
//        try {
//            rabbitTemplate.convertAndSend(exchangeName, routingKey, user);
//        } catch (AmqpException e) {
//            e.printStackTrace();
//        };
//    }
//
//
//    public void smsTTL() {
//        String exchangeName = "ttl_direct_exchange";
//        String routingKey = "sms";
//
//        User user = new User();
//        user.setId(32);
//        user.setName("  smsTTL的  ");
//
//        try {
//            rabbitTemplate.convertAndSend(exchangeName, routingKey, user);
//        } catch (AmqpException e) {
//            e.printStackTrace();
//        };
//    }
//
//}
