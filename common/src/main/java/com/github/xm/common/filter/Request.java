package com.github.xm.common.filter;

/**
 * @author: XuMeng
 * @create: 2018/8/4 17:11
 * @description:
 **/
public interface Request<T> {

    /**
     * @return T 要处理的业务对象
     */
    T get();
}
