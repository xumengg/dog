package com.github.xm.upms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.github.xm.common.util.DataScope;
import com.github.xm.common.util.Query;
import com.github.xm.common.util.UserUtil;
import com.github.xm.common.vo.UserVO;
import com.github.xm.upms.entity.SysUser;
import com.github.xm.upms.mapper.SysUserMapper;
import com.github.xm.upms.service.ISysDeptService;
import com.github.xm.upms.service.ISysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysDeptService deptService;

    @Override
    public Page<UserVO> selectUserVOPage(Query query) {
        DataScope dataScope=new DataScope();
        dataScope.setScopeName("dept_id");
        Integer deptId = UserUtil.currentUserDeptId();
        dataScope.setScopes(deptService.getChildren(deptId));
        return query.setRecords(sysUserMapper.selectUserVOPageScope(query,query.getCondition(),dataScope)) ;
    }

}
