package com.github.xm.common.sender.impl;

import com.github.xm.common.constants.MQQueueConstants;
import com.github.xm.common.sender.ILogVOSender;
import com.github.xm.common.vo.LogVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: XuMeng
 * @create: 2018/7/19 13:23
 * @description:
 **/
@Component("logVOSendImpl")
public class LogVOSenderImpl implements ILogVOSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendLogVO(LogVO logVO) {
        rabbitTemplate.convertAndSend(MQQueueConstants.LOG_QUEUE,logVO);
    }


}
