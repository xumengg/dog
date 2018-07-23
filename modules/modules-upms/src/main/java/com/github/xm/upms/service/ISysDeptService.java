package com.github.xm.upms.service;

import com.github.xm.common.util.TreeNode;
import com.github.xm.upms.entity.SysDept;
import com.baomidou.mybatisplus.service.IService;

import java.util.Set;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-15
 */
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 获取给定部门下的所有子部门的id
     * @param deptId
     * @return
     */
     Set<Integer> getChildren(Integer deptId);


     /**
     * 获取给定部门下的所有子部门树形结构
     * @param depId
     * @return
     */
     TreeNode<SysDept> getTreeNode(Integer depId);


}
