package com.github.xm.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: XuMeng
 * @create: 2018/7/19 10:31
 * @description:
 **/
@Data
public class LogVO implements Serializable {

    private static final long serialVersionUID = 5271379502383140944L;
    /**
     * 主键
     */
    private Integer logId;
    /**
     * 服务id
     */
    private String serviceId;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 用户ip地址
     */
    private String remoteAddr;
    /**
     * 用户代理
     */
    private String userAgent;
    /**
     * 请求uri
     */
    private String requestUri;
    /**
     * 操作方式
     */
    private String method;
    /**
     * 请求数据
     */
    private String params;
    /**
     * 异常信息
     */
    private String exception;
    /**
     * 0:正常 1:删除
     */
    private String delFlag;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
