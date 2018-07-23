package com.github.xm.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: XuMeng
 * @create: 2018/7/15 21:39
 * @description:
 **/
@Slf4j
public class BaseController {

    @Autowired
    private HttpServletRequest request;

}
