package com.github.xm.security.brower.util;

import com.google.code.kaptcha.Producer;
import com.xiaoleilu.hutool.util.RandomUtil;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

/**
 * @author: XuMeng
 * @create: 2018/7/19 21:42
 * @description: 手机验证码
 **/
@Component("smsCodeProducer")
public class SmsCodeProducer implements Producer {

    @Override
    public BufferedImage createImage(String text) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String createText() {
        return RandomUtil.randomNumbers(6);
    }

}
