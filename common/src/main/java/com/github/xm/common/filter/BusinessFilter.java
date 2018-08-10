package com.github.xm.common.filter;

/**
 * @author: XuMeng
 * @create: 2018/8/4 17:13
 * @description:
 **/
public interface BusinessFilter<T> extends Comparable<BusinessFilter>{

    void doFilter(Request<T> request,FilterChain filterChain);

    /**
     *  返回当前过滤器在链中的位置
     * @return
     */
    int getOrder();

    @Override
    default int compareTo(BusinessFilter o) {
        if(getOrder() < o.getOrder()){
            return -1;
        }else if(getOrder() > o.getOrder()){
            return 1;
        }
        return 0;
    }
}

