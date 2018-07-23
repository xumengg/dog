package com.github.xm.common.service.impl;

import com.github.xm.common.mapper.UserDetailsMapper;
import com.github.xm.common.util.Assert;
import com.github.xm.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author: XuMeng
 * @create: 2018/7/14 21:54
 * @description:
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService ,SocialUserDetailsService {

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.isNotBlank(username,"用户名不能为空");
        return userDetailsMapper.selectUserVoByUsername(username);
    }


    public  UserDetails loadUserByPhone(String phone) {
        return userDetailsMapper.selectUserVoByPhone(phone);
    }


    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return userDetailsMapper.selectUserVoByUserId(userId);
    }


    public Integer insert(UserVO userVO){
        return userDetailsMapper.insert(userVO);
    }

}
