package com.example.blogplatform.config;

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
        return new DirectExchange("dead_direct_exchange", true, false);
    }

    @Bean
    public Queue directDeadQueue() {
        return new Queue("wangyi.dead.direct.queue", true);
    }

    @Bean
    public Binding deadBinds() {
        return BindingBuilder.bind(directDeadQueue()).to(deadDirectExchange()).with("dead");
    }

    @Bean
    public Queue emailDirectDeadQueue() {
        return new Queue("email.dead.direct.queue", true);
    }

    @Bean
    public Binding emailDeadBinds() {
        return BindingBuilder.bind(emailDirectDeadQueue()).to(deadDirectExchange()).with("email");
    }

    @Bean
    public Queue smsDirectDeadQueue() {
        return new Queue("sms.dead.direct.queue", true);
    }

    @Bean
    public Binding smsDeadBinds() {
        return BindingBuilder.bind(smsDirectDeadQueue()).to(deadDirectExchange()).with("sms");
    }
}
