package com.github.xm.common.constants;

/**
 * @author: XuMeng
 * @create: 2018/7/28 21:56
 * @description:
 **/
public enum ClientTypeEnum {

    /**
     * 小程序
     */
    applet("1"),

    /**
     * 安卓
     */
    android("2"),

    /**
     * 苹果
     */
    iso("3");

    public String code;

    ClientTypeEnum(String code) {
        this.code = code;
    }
}
