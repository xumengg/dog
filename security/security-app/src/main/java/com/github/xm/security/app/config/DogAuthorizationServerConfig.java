package com.github.xm.security.app.config;

import com.github.xm.common.service.impl.SysClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author: XuMeng
 * @create: 2018/7/26 22:46
 * @description:
 **/
@Configuration
@EnableAuthorizationServer
public class DogAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SysClientServiceImpl sysClientService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
        if(jwtAccessTokenConverter != null){
            endpoints.accessTokenConverter(jwtAccessTokenConverter);
        }

        if(jwtAccessTokenConverter != null){
            DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter)jwtAccessTokenConverter.getAccessTokenConverter();
            DogUserAuthenticationConverter dogUserAuthenticationConverter = new DogUserAuthenticationConverter();
            dogUserAuthenticationConverter.setUserDetailsService(userDetailsService);
            accessTokenConverter.setUserTokenConverter(dogUserAuthenticationConverter);
        }

    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(sysClientService);
    }


}
