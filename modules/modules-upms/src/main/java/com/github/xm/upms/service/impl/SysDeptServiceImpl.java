package com.github.xm.upms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.xm.common.constants.CommomConstants;
import com.github.xm.common.util.TreeNode;
import com.github.xm.upms.entity.SysDept;
import com.github.xm.upms.mapper.SysDeptMapper;
import com.github.xm.upms.service.ISysDeptService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.xm.upms.util.TreeUtil;
import com.xiaoleilu.hutool.collection.CollUtil;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-15
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Override
    public Set<Integer> getChildren(Integer root) {
        Set<Integer> deptIds = new HashSet<>();
        deptIds.add(root);
        getChildren(root, deptIds);
        return deptIds;
    }

    private void getChildren(Integer deptId, Set<Integer> deptIds) {
        SysDept sysDept = new SysDept();
        sysDept.setParentId(deptId);
        sysDept.setDelFlag(CommomConstants.NORMAL);
        List<SysDept> sysDepts = selectList(new EntityWrapper<>(sysDept));
        if (CollUtil.isNotEmpty(sysDepts)) {
            sysDepts.stream().forEach(dept -> {
                deptIds.add(dept.getDeptId());
                getChildren(dept.getDeptId(), deptIds);
            });
        }
    }

    @Override
    public TreeNode<SysDept> getTreeNode(Integer root) {
        SysDept sysDept = selectById(root);
        TreeNode<SysDept> treeNode = TreeUtil.wrap(sysDept);
        setChildren(treeNode);
        return treeNode;
    }


    private void setChildren(TreeNode<SysDept> treeNode) {
        EntityWrapper<SysDept> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parent_id",treeNode.getId());
        entityWrapper.eq("del_flag",CommomConstants.NORMAL);
        List<SysDept> sysDepts = selectList(entityWrapper);
        if(CollUtil.isNotEmpty(sysDepts)){
            List<TreeNode> treeNodes = TreeUtil.wrap(sysDepts);
            treeNode.setChildren(treeNodes);
            treeNodes.stream().forEach( node ->{
                setChildren(node);
            });
        }
    }


}
