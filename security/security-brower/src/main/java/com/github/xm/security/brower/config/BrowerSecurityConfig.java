package com.github.xm.security.brower.config;

import com.github.xm.common.sender.ILogVOSender;
import com.github.xm.common.service.impl.UserDetailsServiceImpl;
import com.github.xm.security.brower.session.DogSessionInformationExpiredStrategy;
import com.github.xm.security.core.authentication.handler.DogAuthenticationFailureHandler;
import com.github.xm.security.core.authentication.handler.DogAuthenticationSuccessHandler;
import com.github.xm.security.brower.filter.ImageValidateCodeFilter;
import com.github.xm.security.brower.filter.RequestLogFilter;
import com.github.xm.security.core.config.SmsCodeAuthenticationConfig;
import com.github.xm.security.core.constants.DogSecurityConstants;
import com.github.xm.security.core.properties.DogSecurityProperties;
import com.github.xm.security.core.validate.code.filter.SmsValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 * @author: XuMeng
 * @create: 2018/7/14 16:20
 * @description:
 **/
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DogSecurityProperties dogSecurityProperties;

    @Autowired
    private DogAuthenticationSuccessHandler dogAuthenticationSuccessHandler;

    @Autowired
    private DogAuthenticationFailureHandler dogAuthenticationFailureHandler;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ILogVOSender logSendServcie;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationConfig smsCodeAuthenticationConfig;

    /**
     * 该类的主要作用是 ， 引导spring处理社交请求
     */
    @Autowired
    private SpringSocialConfigurer socialConfigurer;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        /**
         *  启动的时候用来创建"记住我"的表 ，每次启动都会执行，再第一次启动完成之后就将其注掉
         */
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //请求日志过滤器
        http.addFilterBefore(requestLogFilter(), UsernamePasswordAuthenticationFilter.class)
                //短信验证码过滤器
                .addFilterBefore(smsValidateCodeFilter(), UsernamePasswordAuthenticationFilter.class)
                //图片验证码过滤器
                .addFilterBefore(imageValidateCodeFilter(), UsernamePasswordAuthenticationFilter.class)
                //表单登陆配置
                .formLogin()
                .loginPage(DogSecurityConstants.LOGIN_PAGE)
                .loginProcessingUrl(DogSecurityConstants.LOGIN_PROESSING_URL)
                .successHandler(dogAuthenticationSuccessHandler)
                .failureHandler(dogAuthenticationFailureHandler)
                .and()
                //记住我相关配置
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(dogSecurityProperties.getBrower().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                //session相关配置
                .sessionManagement()
                //.invalidSessionStrategy(invalidSessionStrategy)
                .invalidSessionUrl(DogSecurityConstants.SESSION_INVALID_URL)
                //最大Session数量设置为1 防并发控制
                .maximumSessions(dogSecurityProperties.getSession().getMaximum())
                //当session到达最大值 阻止后面的人登陆
                .maxSessionsPreventsLogin(true)
                .expiredSessionStrategy(new DogSessionInformationExpiredStrategy())
                .and()
                .and()
                //授权相关配置
                .authorizeRequests()
                .antMatchers(DogSecurityConstants.LOGIN_PAGE,
                        dogSecurityProperties.getCode().getSms().getUrl(),
                        dogSecurityProperties.getBrower().getLoginPage(),
                        dogSecurityProperties.getCode().getCode_permit_url(),
                        DogSecurityConstants.SESSION_INVALID_URL)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                //短信验证码相关配置
                .apply(smsCodeAuthenticationConfig)
                .and()
                //社交登陆配置
                .apply(socialConfigurer);
    }


    /**
     * 图片验证码过滤器
     *
     * @return
     * @throws ServletException
     */
    private ImageValidateCodeFilter imageValidateCodeFilter() throws ServletException {
        ImageValidateCodeFilter imageValidateCodeFilter = new ImageValidateCodeFilter();
        imageValidateCodeFilter.setAuthenticationFailureHandler(dogAuthenticationFailureHandler);
        imageValidateCodeFilter.setRedisTemplate(redisTemplate);
        imageValidateCodeFilter.setSecurityProperties(dogSecurityProperties);
        imageValidateCodeFilter.initFilterBean();
        return imageValidateCodeFilter;
    }


    /**
     * 短信验证码过滤器
     *
     * @return
     * @throws ServletException
     */
    private SmsValidateCodeFilter smsValidateCodeFilter() throws ServletException {
        SmsValidateCodeFilter smsValidateCodeFilter = new SmsValidateCodeFilter();
        smsValidateCodeFilter.setAuthenticationFailureHandler(dogAuthenticationFailureHandler);
        smsValidateCodeFilter.setRedisTemplate(redisTemplate);
        smsValidateCodeFilter.setSecurityProperties(dogSecurityProperties);
        smsValidateCodeFilter.setUserDetailsService((UserDetailsServiceImpl) userDetailsService);
        smsValidateCodeFilter.initFilterBean();
        return smsValidateCodeFilter;
    }


    /**
     * 请求日志过滤器
     *
     * @return
     */
    private RequestLogFilter requestLogFilter() {
        RequestLogFilter requestLogFilter = new RequestLogFilter();
        requestLogFilter.setLogSendServcie(logSendServcie);
        return requestLogFilter;
    }

}
