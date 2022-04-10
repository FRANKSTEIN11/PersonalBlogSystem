package com.example.blogplatform.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yenanren
 * @date 2022/4/5 0005
 * @Description
 */
@Slf4j
@Configuration
public class RabbitMqTTLQueueConfig {


    @Bean
    public DirectExchange ttlDirectExchange() {
        return new DirectExchange("ttl_direct_exchange", true, false);
    }

    @Bean
    public Queue directTTLQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 15000);
        args.put("x-dead-letter-exchange", "dead_direct_exchange");
        args.put("x-dead-letter-routing-key", "dead");
        return new Queue("wangyi.ttl.direct.queue", true, false, false, args);
    }

    @Bean
    public Binding ttlBings() {
        return BindingBuilder.bind(directTTLQueue()).to(ttlDirectExchange()).with("ttl");
    }


    @Bean
    public Queue emailDirectTTLQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 5000);
        args.put("x-dead-letter-exchange", "dead_direct_exchange");
        args.put("x-dead-letter-routing-key", "email");
        return new Queue("email.ttl.direct.queue", true, false, false, args);
    }

    @Bean
    public Binding emailTTLBings() {
        return BindingBuilder.bind(emailDirectTTLQueue()).to(ttlDirectExchange()).with("email");
    }

    @Bean
    public Queue smsDirectTTLQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 5000);
        args.put("x-dead-letter-exchange", "dead_direct_exchange");
        args.put("x-dead-letter-routing-key", "sms");
        return new Queue("sms.ttl.direct.queue", true, false, false, args);
    }

    @Bean
    public Binding smsTTLBings() {
        return BindingBuilder.bind(smsDirectTTLQueue()).to(ttlDirectExchange()).with("sms");
    }

}


