package com.areaofit.rabbitmq.consumer;

import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.Return;
import com.rabbitmq.client.ReturnCallback;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 生产发送消息确认机制：
 * 监听生产者是否发送消息到 exchange 和 queue 上，以便做下一步的业务处理。
 * 生产者和消费者的代码不变，开启发送确认和发送回调的配置开关：
 * 发送确认：spring.rabbitmq.publisher-confirms=true
 * 发送回调：spring.rabbitmq.publisher-returns=true
 *
 * 新建 bean 实现 ConfirmCallback 和 ReturnCallback 接口
 *
 */
@Component
public class SendMsgConfirm implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.setReturnCallback(this::returnedMessage);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送成功：" + correlationData);
        } else {
            System.out.println("消息发送成功：" + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("exchange: " + exchange);
    }
}
