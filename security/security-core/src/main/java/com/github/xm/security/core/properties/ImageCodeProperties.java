package com.github.xm.security.core.properties;

import lombok.Data;

/**
 * @author: XuMeng
 * @create: 2018/7/18 22:45
 * @description:
 **/
@Data
public class ImageCodeProperties {

    /**
     * 需要通过验证码验证的url
     */
    String url = "/authentication/form";
}
