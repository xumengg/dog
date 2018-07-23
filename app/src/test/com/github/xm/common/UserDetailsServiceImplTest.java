package com.github.xm.common;

import com.github.xm.common.service.impl.UserDetailsServiceImpl;
import com.github.xm.common.util.FormatJson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: XuMeng
 * @create: 2018/7/16 9:44
 * @description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Test
    public void loadUserByUserNameTest(){
        UserDetails userDetails = userDetailsService.loadUserByUsername("徐猛");
        FormatJson.printConsole(userDetails);
    }


}
