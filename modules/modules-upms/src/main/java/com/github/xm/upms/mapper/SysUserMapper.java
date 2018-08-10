package com.github.xm.upms.mapper;

import com.github.xm.common.util.DataScope;
import com.github.xm.common.util.Query;
import com.github.xm.common.vo.UserVO;
import com.github.xm.upms.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-15
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List selectUserVOPageScope(Query query,@Param("p") Map params,DataScope dataScope);

}
