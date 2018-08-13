package com.github.xm.security.core.social;

import com.github.xm.common.util.UserUtil;
import com.github.xm.security.core.authentication.handler.DogAuthenticationSuccessHandler;
import com.github.xm.security.core.properties.DogSecurityProperties;
import com.github.xm.security.core.social.support.DogSpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author: XuMeng
 * @create: 2018/7/22 15:10
 * @description:
 **/
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DogSecurityProperties securityProperties;

    @Autowired
    private ConnectionSignUp connectionSignUp;

    @Autowired
    private DogAuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    @Bean
    @Primary
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(
                dataSource, connectionFactoryLocator, Encryptors.noOpText());
        jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
        return jdbcUsersConnectionRepository;
    }

    /**
     * 该类的主要作用是 ， 引导spring处理社交请求
     */
    @Bean
    public SpringSocialConfigurer springSocialConfigurer() {
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        DogSpringSocialConfigurer dogSpringSocialConfigurer = new DogSpringSocialConfigurer(filterProcessesUrl,
                authenticationSuccessHandler);
        return dogSpringSocialConfigurer;
    }

    /**
     * 用来处理注册流程的工具类
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator,
                getUsersConnectionRepository(connectionFactoryLocator));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new UserIdSource() {
            @Override
            public String getUserId() {
                return UserUtil.currentUser().getUserId();
            }
        };
    }
}
