package com.github.xm.upms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.github.xm.common.util.Query;
import com.github.xm.common.vo.UserVO;
import com.github.xm.upms.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-15
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 分页查询用户列表
     * @param query
     * @param userVO
     * @return
     */
    Page<UserVO> selectUserVOPage(Query query,UserVO userVO);
}
