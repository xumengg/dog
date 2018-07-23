package com.github.xm.upms.util;

import com.github.xm.common.util.TreeNode;
import com.github.xm.upms.entity.SysDept;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: XuMeng
 * @create: 2018/7/20 16:50
 * @description:
 **/
public class TreeUtil {

    /**
     *  将sysDept包装成树节点
     * @param sysDept
     * @return
     */
    public static TreeNode<SysDept> wrap(SysDept sysDept){
        TreeNode<SysDept> treeNode = new TreeNode();
        treeNode.setId(sysDept.getDeptId());
        treeNode.setName(sysDept.getDeptName());
        treeNode.setData(sysDept);
        return treeNode;
    }


    /**
     *
     * @param sysDepts
     * @return
     */
    public static List<TreeNode> wrap(List<SysDept> sysDepts){
        return sysDepts.stream().map(sysDept -> wrap(sysDept)).collect(Collectors.toList());
    }
}
