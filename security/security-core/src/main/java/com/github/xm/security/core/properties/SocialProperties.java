package com.github.xm.security.core.properties;

import lombok.Data;

/**
 * @author: XuMeng
 * @create: 2018/7/22 15:14
 * @description:
 **/
@Data
public class SocialProperties {

    private  String filterProcessesUrl  = "/auth";

    private QQProperties qq = new QQProperties();
}
