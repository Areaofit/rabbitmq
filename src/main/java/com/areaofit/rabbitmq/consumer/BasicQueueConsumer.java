package com.areaofit.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BasicQueueConsumer {

    @RabbitListener(queues = "test")
    public void process(Message msg, Channel channel) throws Exception{
        System.out.println("receive message: " + new String(msg.getBody()));
        Thread.sleep(20*1000L);
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
    }
}
