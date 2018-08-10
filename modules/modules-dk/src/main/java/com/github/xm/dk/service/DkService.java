package com.github.xm.dk.service;

import com.github.xm.common.filter.BusinessFilter;
import com.github.xm.common.filter.FilterChain;
import com.github.xm.common.util.FormatJson;
import com.github.xm.dk.entity.DkConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: XuMeng
 * @create: 2018/8/4 17:23
 * @description:
 **/
@Service
public class DkService {

    @Autowired
    private Map<String,BusinessFilter<DkConsumer>> map;

    public  void filterChainExample(){
        DkConsumerRequest dkConsumerRequest = new DkConsumerRequest(new DkConsumer());
        List<BusinessFilter> list= new ArrayList<>(map.values());
        FilterChain filterChain=new FilterChain(list,dkConsumerRequest);
        filterChain.doFilter();
        DkConsumer request = (DkConsumer)filterChain.getRequest().get();
        FormatJson.printConsole(request);
    }

}
