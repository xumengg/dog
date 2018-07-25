package com.github.xm.security.core.properties;

import lombok.Data;

/**
 * @author: XuMeng
 * @create: 2018/7/25 23:18
 * @description:
 **/
@Data
public class SessionProperties {
    /**
     * 登陆用户最大保持session数
     */
    int maximum = 1;
}
