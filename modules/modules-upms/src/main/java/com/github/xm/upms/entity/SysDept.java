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
 * 部门表
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-15
 */
@TableName("sys_dept")
@Data
public class SysDept extends Model<SysDept> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;
    /**
     * 部门名称
     */
    @TableField("dept_name")
    private String deptName;
    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;
    /**
     * 父id
     */
    @TableField("parent_id")
    private Integer parentId;
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
        return this.deptId;
    }
}
