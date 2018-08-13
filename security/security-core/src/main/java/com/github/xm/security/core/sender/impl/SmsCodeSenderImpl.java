package com.github.xm.security.core.sender.impl;

import com.github.xm.common.constants.MQQueueConstants;
import com.github.xm.security.core.sender.SmsCodeSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: XuMeng
 * @create: 2018/7/19 22:08
 * @description:
 **/
@Component
public class SmsCodeSenderImpl implements SmsCodeSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendSmsCode(String smsCode) {
        rabbitTemplate.convertAndSend(MQQueueConstants.SMS_QUEUE,smsCode);
    }
}
