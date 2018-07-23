package com.github.xm.security.brower.listener;

import com.github.xm.security.core.constants.DogSecurityConstants;
import com.github.xm.common.constants.MQQueueConstants;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: XuMeng
 * @create: 2018/7/19 22:28
 * @description:
 **/
@Component
@RabbitListener(queues = MQQueueConstants.SMS_QUEUE)
@Slf4j
public class SmsCodeQueueListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitHandler
    public void handler(String smsCode){
        String[] strs = StrUtil.split(smsCode, ",");
        String phone=strs[0];
        String sms=strs[1];

        //具体按实际接入的短信平台进行发送
        log.info("正在给手机号【{}】发送的验证码【{}】,send...",phone,sms);

        //将手机号进行缓存
        redisTemplate.opsForValue().set(DogSecurityConstants.DEFAULT_SMS_KEY+phone,sms,DogSecurityConstants.DEFAULT_SMS_EXPIRE, TimeUnit.SECONDS);
    }


}
