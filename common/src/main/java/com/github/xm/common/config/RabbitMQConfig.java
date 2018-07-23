package com.github.xm.common.config;

import com.github.xm.common.constants.MQQueueConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: XuMeng
 * @create: 2018/7/19 10:39
 * @description:
 **/
@Configuration
public class RabbitMQConfig {

    /**
     * 日志队列
     * @return
     */
    @Bean
    public Queue logQueue(){
        return  new Queue(MQQueueConstants.LOG_QUEUE);
    }


    /**
     * 短信验证码队列
     * @return
     */
    @Bean
    public Queue smsQueue(){
        return  new Queue(MQQueueConstants.SMS_QUEUE);
    }

}
