package com.github.xm.common.exception;

/**
 * @author: XuMeng
 * @create: 2018/7/15 12:48
 * @description:
 **/
public class CheckException extends RuntimeException {

    public CheckException(String msg){
        super(msg);
    }

    public  CheckException(Throwable throwable){
        super(throwable);
    }

    public CheckException(String msg,Throwable throwable){
        super(msg,throwable);
    }

}
