package com.github.xm.dk.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: XuMeng
 * @create: 2018/7/27 17:28
 * @description:
 **/
@Data
public class dkConsumer implements Serializable {

    private static final long serialVersionUID = 3753103603936801367L;

    private String name;

    private String wxNickName;
}
