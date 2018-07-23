package com.github.xm.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author: XuMeng
 * @create: 2018/7/18 21:45
 * @description:
 **/
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 3138028446783845188L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
