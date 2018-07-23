package com.github.xm.common.mapper;

import com.github.xm.common.vo.UserVO;

/**
 * @author: XuMeng
 * @create: 2018/7/14 21:57
 * @description:
 **/
public interface UserDetailsMapper {

    UserVO selectUserVoByUsername(String username);

    UserVO selectUserVoByPhone(String phone);

    UserVO selectUserVoByUserId(String userId);

    Integer insert(UserVO userVO);
}
