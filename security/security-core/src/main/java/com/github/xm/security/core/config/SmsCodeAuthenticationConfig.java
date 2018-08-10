package com.github.xm.security.core.config;

import com.github.xm.security.core.authentication.handler.DogAuthenticationFailureHandler;
import com.github.xm.security.core.authentication.handler.DogAuthenticationSuccessHandler;
import com.github.xm.security.core.authentication.phone.SmsCodeAuthenticationFilter;
import com.github.xm.security.core.authentication.phone.SmsCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: XuMeng
 * @create: 2018/7/21 11:03
 * @description:
 **/
@Component
public class SmsCodeAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DogAuthenticationFailureHandler dogAuthenticationFailureHandler;

    @Autowired
    private Map<String,AuthenticationSuccessHandler> authenticationSuccessHandlerMap;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        /**
         * 获取的是App成功登陆处理器
         */
        AuthenticationSuccessHandler dogAuthenticationSuccessHandler2 = authenticationSuccessHandlerMap.get("dogAuthenticationSuccessHandler2");

        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(dogAuthenticationSuccessHandler2);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(dogAuthenticationFailureHandler);

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
