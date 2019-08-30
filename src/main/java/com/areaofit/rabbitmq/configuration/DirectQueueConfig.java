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

    public final static String DIRECT_EXCHANGE_NAME = "direct_exchange_test";

    @Bean
    public Queue directQueue1() {
        return new Queue(QUEUE_1_NAME, true);
    }

    @Bean
    public Queue directQueue2() {
        return new Queue(QUEUE_2_NAME, true);
    }

    @Bean
    public DirectExchange directExchange() {
        DirectExchange exchange = new DirectExchange(DIRECT_EXCHANGE_NAME);
        return exchange;
    }

    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with(QUEUE_1_NAME);
    }

    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with(QUEUE_1_NAME);
    }

    @Bean
    public Binding directBinding3() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with(QUEUE_2_NAME);
    }

}
