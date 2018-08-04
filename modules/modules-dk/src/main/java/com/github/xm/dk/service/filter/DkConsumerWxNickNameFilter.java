package com.github.xm.dk.service.filter;

import com.github.xm.common.filter.BusinessFilter;
import com.github.xm.common.filter.FilterChain;
import com.github.xm.common.filter.Request;
import com.github.xm.dk.entity.DkConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: XuMeng
 * @create: 2018/8/4 17:25
 * @description:
 **/
@Component
@Slf4j
public class DkConsumerWxNickNameFilter implements BusinessFilter <DkConsumer> {

    @Override
    public void doFilter(Request<DkConsumer> request, FilterChain filterChain) {
        DkConsumer dkConsumer = request.get();
        dkConsumer.setWxNickName("微信_张三");
        log.info("DkConsumerWxNickNameFilter执行了");
        filterChain.doFilter();
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
