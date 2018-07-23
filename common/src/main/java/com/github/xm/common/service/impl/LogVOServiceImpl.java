package com.github.xm.common.service.impl;

import com.github.xm.common.mapper.LogVOMapper;
import com.github.xm.common.service.ILogVOService;
import com.github.xm.common.vo.LogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: XuMeng
 * @create: 2018/7/19 11:09
 * @description:
 **/
@Service
public class LogVOServiceImpl implements ILogVOService {

    @Autowired
    private LogVOMapper logVOMapper;

    @Override
    public void insert(LogVO logVO) {
        logVOMapper.insert(logVO);
    }

}
