package com.github.xm.security.core.authentication.handler;

import com.github.xm.common.service.impl.UserDetailsServiceImpl;
import com.github.xm.common.vo.UserVO;
import com.github.xm.security.core.constants.DogSecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @author: XuMeng
 * @create: 2018/7/23 20:44
 * @description:  用来解决QQ用户用来关联系统用户的逻辑
 *
 **/
@Component
public class DogConnectionSignUp  implements ConnectionSignUp {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public String execute(Connection<?> connection) {
        UserVO userVO=new UserVO();
        userVO.setUsername(DogSecurityConstants.QQ_USERNAME_KEY+connection.getDisplayName());
        userVO.setAvatar(connection.getImageUrl());
        userDetailsService.insert(userVO);
        return userVO.getUserId();
    }
}
