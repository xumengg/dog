package com.github.xm.common.sender;

import com.github.xm.common.vo.LogVO;

/**
 * @author: XuMeng
 * @create: 2018/7/19 13:20
 * @description:
 **/
public interface ILogVOSender {

    /**
     * 将日志消息push到队列
     * @param logVO
     */
    void sendLogVO(LogVO logVO);

}
