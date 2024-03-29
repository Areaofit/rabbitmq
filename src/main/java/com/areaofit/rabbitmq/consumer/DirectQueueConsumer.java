package com.areaofit.rabbitmq.consumer;

import com.areaofit.rabbitmq.configuration.DirectQueueConfig;
import com.areaofit.rabbitmq.configuration.FanoutQueueConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DirectQueueConsumer {

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = DirectQueueConfig.QUEUE_1_NAME),
            exchange = @Exchange(value = DirectQueueConfig.DIRECT_EXCHANGE_NAME))},
            containerFactory = "rabbitListenerContainerFactory")
    public void process1(Channel channel, Message message) throws Exception {
        log.info("\nexchangeType: {}\nqueueName: {}\nmessage: {}",
                DirectQueueConfig.DIRECT_EXCHANGE_NAME,
                DirectQueueConfig.QUEUE_1_NAME,
                new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = DirectQueueConfig.QUEUE_2_NAME),
            exchange = @Exchange(value = DirectQueueConfig.DIRECT_EXCHANGE_NAME))},
            containerFactory = "rabbitListenerContainerFactory")
    public void process2(Channel channel, Message message) throws Exception {
        log.info("\nexchangeType: {}\nqueueName: {}\nmessage: {}",
                DirectQueueConfig.DIRECT_EXCHANGE_NAME,
                DirectQueueConfig.QUEUE_2_NAME,
                new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
