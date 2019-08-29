package com.areaofit.rabbitmq.consumer;

import com.areaofit.rabbitmq.configuration.DirectQueueConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectQueueConsumer {

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = DirectQueueConfig.QUEUE_1_NAME),
            exchange = @Exchange(value = DirectQueueConfig.DIRECT_EXCHANGE_NAME))})
    public void process1(Channel channel, Message message) throws Exception {
        System.out.println("direct queue " + DirectQueueConfig.QUEUE_1_NAME +
                " : " +
                new String(message.getBody()));
        // 手动确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = DirectQueueConfig.QUEUE_2_NAME),
            exchange = @Exchange(value = DirectQueueConfig.DIRECT_EXCHANGE_NAME))})
    public void process2(Channel channel, Message message) throws Exception {
        System.out.println("direct queue " + DirectQueueConfig.QUEUE_1_NAME + " and "+
                DirectQueueConfig.QUEUE_2_NAME +
                " : " +
                new String(message.getBody()));
        // 手动确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
