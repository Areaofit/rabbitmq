package com.areaofit.rabbitmq.consumer;

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
public class FanoutQueueConsumer {

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = FanoutQueueConfig.FANOUT_QUEUE_1),
            exchange = @Exchange(value = FanoutQueueConfig.FANOUT_EXCHANGE_NAME, type = ExchangeTypes.FANOUT))})
    public void process1(Channel channel, Message message) throws Exception {
        log.info("\nexchangeType: {}\nqueueName: {}\nmessage: {}",
                FanoutQueueConfig.FANOUT_EXCHANGE_NAME,
                FanoutQueueConfig.FANOUT_QUEUE_1,
                new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = FanoutQueueConfig.FANOUT_QUEUE_2),
            exchange = @Exchange(value = FanoutQueueConfig.FANOUT_EXCHANGE_NAME, type = ExchangeTypes.FANOUT))})
    public void process2(Channel channel, Message message) throws Exception {
        log.info("\nexchangeType: {}\nqueueName: {}\nmessage: {}",
                FanoutQueueConfig.FANOUT_EXCHANGE_NAME,
                FanoutQueueConfig.FANOUT_QUEUE_2,
                new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
