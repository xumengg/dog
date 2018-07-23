package com.github.xm.common.interceptor;

import com.github.xm.common.util.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: XuMeng
 * @create: 2018/7/20 17:54
 * @description:
 **/
@Slf4j
@RestControllerAdvice
public class GlobExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ServerResponse<String> handler(Exception e){
        log.error("全局异常信息:",e);
        return ServerResponse.failure(e);
    }
}
