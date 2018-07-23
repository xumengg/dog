package com.github.xm.upms.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色部门关联表
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-15
 */
@TableName("sys_role_dept")
public class SysRoleDept extends Model<SysRoleDept> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "role_dept_id", type = IdType.AUTO)
    private Integer roleDeptId;
    /**
     * 角色id
     */
    @TableField("role_id")
    private Integer roleId;
    @TableField("dept_id")
    private Integer deptId;
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


    public Integer getRoleDeptId() {
        return roleDeptId;
    }

    public void setRoleDeptId(Integer roleDeptId) {
        this.roleDeptId = roleDeptId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.roleDeptId;
    }

    @Override
    public String toString() {
        return "SysRoleDept{" +
        ", roleDeptId=" + roleDeptId +
        ", roleId=" + roleId +
        ", deptId=" + deptId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
