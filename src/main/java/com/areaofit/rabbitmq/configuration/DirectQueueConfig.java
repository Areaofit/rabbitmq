package com.areaofit.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * direct 模式：
 * Todo 总结自己的理解
 */
@Configuration
public class DirectQueueConfig {

    public final static String QUEUE_1_NAME = "direct.first";

    public final static String QUEUE_2_NAME = "direct.second";

    public final static String DIRECT_EXCHANGE_NAME = "direct";

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_1_NAME, true);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_2_NAME, true);
    }

    @Bean
    public DirectExchange exchange() {
        DirectExchange exchange = new DirectExchange(DIRECT_EXCHANGE_NAME);
        return exchange;
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(exchange()).with(QUEUE_1_NAME);
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(exchange()).with(QUEUE_1_NAME);
    }

    @Bean
    public Binding binding3() {
        return BindingBuilder.bind(queue2()).to(exchange()).with(QUEUE_2_NAME);
    }

}
