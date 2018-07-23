package com.github.xm.common.listener;

import com.github.xm.common.constants.MQQueueConstants;
import com.github.xm.common.service.ILogVOService;
import com.github.xm.common.vo.LogVO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: XuMeng
 * @create: 2018/7/19 10:53
 * @description:
 **/
@Component
@RabbitListener(queues = MQQueueConstants.LOG_QUEUE)
public class LogQueueListener {

    @Autowired
    private ILogVOService logVOService;

    /**
     * 将用户请求日志保存到数据库
     * @param logVO
     */
    @RabbitHandler
    public void handler(LogVO logVO){
        logVOService.insert(logVO);
    }

}
