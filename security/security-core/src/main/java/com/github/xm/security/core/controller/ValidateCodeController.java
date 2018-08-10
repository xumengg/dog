package com.github.xm.security.core.controller;

import com.baomidou.mybatisplus.toolkit.IOUtils;
import com.github.xm.common.util.Assert;
import com.github.xm.security.core.constants.DogSecurityConstants;
import com.github.xm.security.core.sender.SmsCodeSender;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: XuMeng
 * @create: 2018/7/17 22:54
 * @description:
 **/
@Controller
public class ValidateCodeController {

    @Autowired
    private Map<String,Producer> producerMap;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @GetMapping("/code/image/{randomStr}")
    public void createImageCode(@PathVariable String randomStr, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Assert.isNotBlank(randomStr,"随机KEY不能为空");
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        String text = producerMap.get("imageCodeProducer").createText();
        BufferedImage image = producerMap.get("imageCodeProducer").createImage(text);

        /**
         * 将验证码放入缓存
         */
        String key = DogSecurityConstants.DEFAULT_CODE_KEY + randomStr;
        redisTemplate.opsForValue().set(key,text,DogSecurityConstants.DEFAULT_IMAGE_EXPIRE,TimeUnit.SECONDS);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPEG", out);
        IOUtils.closeQuietly(out);
    }


    @GetMapping("/code/sms")
    public void createSmsCode( HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        String phone = ServletRequestUtils.getRequiredStringParameter(request, "phone");
        String sms = producerMap.get("smsCodeProducer").createText();
        smsCodeSender.sendSmsCode(phone+","+sms);
    }


}
