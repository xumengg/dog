package com.github.xm.security.app.config;

import com.github.xm.common.service.impl.SysClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author: XuMeng
 * @create: 2018/7/26 22:46
 * @description:
 **/
@Configuration
@EnableAuthorizationServer
public class DogAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private SysClientServiceImpl sysClientService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(sysClientService);
    }
}
