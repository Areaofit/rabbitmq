package com.areaofit.rabbitmq.controller;

import com.areaofit.rabbitmq.common.ResponseData;
import com.areaofit.rabbitmq.common.Utils;
import com.areaofit.rabbitmq.configuration.DirectQueueConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Api(tags = "RabbitSendController", description = "MQ消息发送接口")
public class RabbitSendController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/basicQueue")
    @ApiOperation(value = "简单Queue模式消息发送", notes = "队列名：test")
    public ResponseData testBasicQueue(
            @ApiParam(name = "msg", value = "消息内容", required = true)
            @RequestParam(name = "msg", required = true) String msg) {
        rabbitTemplate.convertAndSend("test", msg);
        return Utils.successData(null);
    }

    @PutMapping("/directQueue")
    @ApiOperation(value = "direct模式消息发送", notes = "队列1：direct.first\n队列2：direct.second", response = ResponseData.class)
    public ResponseData testDirectQueue(
            @ApiParam(name = "msg", value = "消息内容", required = true)
            @RequestParam(name = "msg", required = true) String msg,
            @ApiParam(name = "queue", value = "队列名", required = true)
            @RequestParam(name = "queue", required = true) String queue) {
        rabbitTemplate.convertAndSend(DirectQueueConfig.DIRECT_EXCHANGE_NAME, queue, msg);
        return Utils.successData(null);
    }
}
