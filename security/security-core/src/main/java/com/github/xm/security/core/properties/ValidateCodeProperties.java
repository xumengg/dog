package com.github.xm.security.core.properties;

import lombok.Data;

/**
 * @author: XuMeng
 * @create: 2018/7/18 22:45
 * @description:
 **/
@Data
public class ValidateCodeProperties {

    /**
     *  获取验证码接口 不需要身份认证
     */
    private String code_permit_url = "/code/**";

    private ImageCodeProperties image=new ImageCodeProperties();

    private SmsCodeProperties sms =new SmsCodeProperties();
}
