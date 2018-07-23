package com.github.xm.security.core.properties;

import lombok.Data;

/**
 * @author: XuMeng
 * @create: 2018/7/21 10:12
 * @description:
 **/
@Data
public class SmsCodeProperties {


    /**
     * 需要通过验证码验证的url
     */
    String url = "/authentication/phone";

}
