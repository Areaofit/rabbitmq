package com.areaofit.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BasicQueueConsumer {

    @RabbitListener(queues = "test")
    public void process(Message msg, Channel channel) throws Exception{
        log.info("\nexchangeType: {}\nqueueName: {}\nmessage: {}", "system_default_exchange", "test", new String(msg.getBody()));
//        Thread.sleep(20*1000L);
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
    }
}
