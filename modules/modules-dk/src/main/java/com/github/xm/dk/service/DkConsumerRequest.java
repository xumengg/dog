package com.github.xm.dk.service;

import com.github.xm.common.filter.Request;
import com.github.xm.dk.entity.DkConsumer;

/**
 * @author: XuMeng
 * @create: 2018/8/4 17:23
 * @description:
 **/
public class DkConsumerRequest implements Request<DkConsumer> {

    private DkConsumer dkConsumer;

    public DkConsumerRequest(DkConsumer dkConsumer) {
        this.dkConsumer = dkConsumer;
    }

    @Override
    public DkConsumer get() {
        return dkConsumer;
    }
}
