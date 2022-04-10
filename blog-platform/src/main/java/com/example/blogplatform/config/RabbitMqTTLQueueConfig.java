package com.example.blogplatform.config;

import com.example.blogplatform.conf.Conf;
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
    /**
     * 队列元素存时长
     */
    private static final int TTL_TIME = 60000;

    @Bean
    public DirectExchange ttlDirectExchange() {
        return new DirectExchange(Conf.TTL_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Queue viewsDirectTTLQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", TTL_TIME);
        args.put("x-dead-letter-exchange", Conf.DEAD_DIRECT_EXCHANGE);
        args.put("x-dead-letter-routing-key", Conf.VIEWS_ROUTING_KEY);
        return new Queue(Conf.VIEWS_TTL_DIRECT_QUEUE, true, false, false, args);
    }

    @Bean
    public Queue thumbsupDirectTTLQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", TTL_TIME);
        args.put("x-dead-letter-exchange", Conf.DEAD_DIRECT_EXCHANGE);
        args.put("x-dead-letter-routing-key", Conf.THUMBSUP_ROUTING_KEY);
        return new Queue(Conf.THUMBSUP_TTL_DIRECT_QUEUE, true, false, false, args);
    }

    @Bean
    public Queue collectionDirectTTLQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", TTL_TIME);
        args.put("x-dead-letter-exchange", Conf.DEAD_DIRECT_EXCHANGE);
        args.put("x-dead-letter-routing-key", Conf.COLLECTION_ROUTING_KEY);
        return new Queue(Conf.COLLECTION_TTL_DIRECT_QUEUE, true, false, false, args);
    }

    @Bean
    public Binding viewsTTLBings() {
        return BindingBuilder.bind(viewsDirectTTLQueue()).to(ttlDirectExchange()).with(Conf.VIEWS_ROUTING_KEY);
    }

    @Bean
    public Binding thumbsupTTLBings() {
        return BindingBuilder.bind(thumbsupDirectTTLQueue()).to(ttlDirectExchange()).with(Conf.THUMBSUP_ROUTING_KEY);
    }

    @Bean
    public Binding collectionTTLBings() {
        return BindingBuilder.bind(collectionDirectTTLQueue()).to(ttlDirectExchange()).with(Conf.COLLECTION_ROUTING_KEY);
    }

}


