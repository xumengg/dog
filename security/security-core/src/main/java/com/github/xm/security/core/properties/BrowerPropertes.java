package com.github.xm.security.core.properties;

import lombok.Data;

/**
 * @author: XuMeng
 * @create: 2018/7/16 23:12
 * @description:
 **/
@Data
public class BrowerPropertes {

    /**
     *  登陆页面
     */
    private String loginPage = "/dog-singIn.html";

    /**
     * 登陆成功或失败后的响应方式
     */
    private LoginResponseType  loginResponseType = LoginResponseType.JSON;


    /**
     * 记住我默认时间7天
     */
    private Integer rememberMeSeconds = 60*60*24*7;

}
