package com.github.xm.common.filter;

import java.util.Collections;
import java.util.List;

/**
 * @author: XuMeng
 * @create: 2018/8/4 17:14
 * @description:
 **/
public class FilterChain {

    private List<BusinessFilter> filters;

    private int indext = 0;

    private Request request;

    private int size = 0;

    public FilterChain(List<BusinessFilter> list, Request request) {
        Collections.sort(list);
        this.filters = list;
        this.request = request;
        this.size= filters.size();
    }


    public void doFilter(){
        if(indext >= size){
            return;
        }
        filters.get(indext++).doFilter(request,this);
    }

    public Request getRequest() {
        return request;
    }

}
