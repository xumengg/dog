package com.github.xm.upms.entity;

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
 * 角色部门关联表
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-15
 */
@TableName("sys_role_dept")
@Data
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


    @Override
    protected Serializable pkVal() {
        return this.roleDeptId;
    }

}
