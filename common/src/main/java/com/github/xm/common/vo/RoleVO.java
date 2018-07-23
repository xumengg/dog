package com.github.xm.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: XuMeng
 * @create: 2018/7/14 17:07
 * @description:
 **/
@Data
public class RoleVO implements Serializable {

    private static final long serialVersionUID = -7233864234387914961L;

    private Integer roleId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private Date createTime;
    private Date updateTime;
    private String delFlag;
}
