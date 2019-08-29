package com.areaofit.rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基本的 direct 模式，只需要声明一个 queue 的名字，会与 RabbitMQ 的默认 exchange 进行绑定，
 * routingkey 和 bindingkey 是相同的。
 */
@Configuration
public class BasicQueueConfig {

    @Bean
    public Queue queue() {
        return new Queue("test", true);
    }
}
