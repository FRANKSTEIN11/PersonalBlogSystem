package com.example.blogplatform.config;

import com.example.blogplatform.conf.Conf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yenanren
 * @date 2022/4/5 0005
 * @Description
 */

@Slf4j
@Configuration
public class RabbitMqDeadQueueConfig {

    @Bean
    public DirectExchange deadDirectExchange() {
        return new DirectExchange(Conf.DEAD_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Queue viewsDirectDeadQueue() {
        return new Queue(Conf.VIEWS_DEAD_DIRECT_QUEUE, true);
    }

    @Bean
    public Queue thumbsupDirectDeadQueue() {
        return new Queue(Conf.THUMBSUP_DEAD_DIRECT_QUEUE, true);
    }

    @Bean
    public Queue collectionDirectDeadQueue() {
        return new Queue(Conf.COLLECTION_DEAD_DIRECT_QUEUE, true);
    }

    @Bean
    public Binding viewsDeadBinds() {
        return BindingBuilder.bind(viewsDirectDeadQueue()).to(deadDirectExchange()).with(Conf.VIEWS_ROUTING_KEY);
    }

    @Bean
    public Binding thumbsupDeadBinds() {
        return BindingBuilder.bind(thumbsupDirectDeadQueue()).to(deadDirectExchange()).with(Conf.THUMBSUP_KEY);
    }

    @Bean
    public Binding collectionDeadBinds() {
        return BindingBuilder.bind(collectionDirectDeadQueue()).to(deadDirectExchange()).with(Conf.COLLECTION_KEY);
    }
}
