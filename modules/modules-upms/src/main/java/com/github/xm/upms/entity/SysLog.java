package com.github.xm.upms.entity;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-19
 */
@TableName("sys_log")
@Data
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;
    /**
     * 服务id
     */
    @TableField("service_id")
    private String serviceId;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 用户ip地址
     */
    @TableField("remote_addr")
    private String remoteAddr;
    /**
     * 用户代理
     */
    @TableField("user_agent")
    private String userAgent;
    /**
     * 请求uri
     */
    @TableField("request_uri")
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
     * 0:正常 1:删除 9:锁定
     */
    @TableLogic
    @TableField("del_flag")
    private String delFlag;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

}
