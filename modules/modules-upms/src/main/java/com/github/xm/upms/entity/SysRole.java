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
 * 角色
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-15
 */
@TableName("sys_role")
@Data
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;
    /**
     * 角色名
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 角色编码 （ 用户权限 指的就是这个字段）
     */
    @TableField("role_code")
    private String roleCode;
    @TableField("role_desc")
    private String roleDesc;
    /**
     * 0:正常 1:删除
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
        return this.roleId;
    }

}
