package com.github.xm.security.app.config;

import com.github.xm.security.app.authentication.handler.DogAuthenticationSuccessHandler;
import com.github.xm.security.core.authentication.handler.DogAuthenticationFailureHandler;
import com.github.xm.security.core.config.SmsCodeAuthenticationConfig;
import com.github.xm.security.core.constants.DogSecurityConstants;
import com.github.xm.security.core.properties.DogSecurityProperties;
import com.xiaoleilu.hutool.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author: XuMeng
 * @create: 2018/7/28 15:59
 * @description:
 **/
@Configuration
@EnableResourceServer
public class DogAuthenticationResourceServerConfig  extends ResourceServerConfigurerAdapter {

    @Autowired
    private DogAuthenticationSuccessHandler dogAuthenticationSuccessHandler;

    @Autowired
    private DogAuthenticationFailureHandler dogAuthenticationFailureHandler;

    @Autowired
    private DogSecurityProperties dogSecurityProperties;

    @Autowired
    private SmsCodeAuthenticationConfig smsCodeAuthenticationConfig;

    /**
     * 该类的主要作用是 ， 引导spring处理社交请求
     */
    @Autowired
    private SpringSocialConfigurer socialConfigurer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //请求日志过滤器
        http
                //图片验证码过滤器
              //  .addFilterBefore(imageValidateCodeFilter(), UsernamePasswordAuthenticationFilter.class)
                //表单登陆配置
                .formLogin()
                .loginPage(DogSecurityConstants.LOGIN_PAGE)
                .loginProcessingUrl(DogSecurityConstants.LOGIN_PROESSING_URL)
                .successHandler(dogAuthenticationSuccessHandler)
                .failureHandler(dogAuthenticationFailureHandler)
                .and()
                //授权相关配置
                .authorizeRequests()
                .antMatchers(DogSecurityConstants.LOGIN_PAGE,
                        dogSecurityProperties.getCode().getSms().getUrl(),
                        dogSecurityProperties.getBrower().getLoginPage(),
                        dogSecurityProperties.getCode().getCode_permit_url(),
                        DogSecurityConstants.SESSION_INVALID_URL,
                        DogSecurityConstants.LOGOUT_URL)
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


    public static void main(String[] args) {
        String a = "fa651479-53ab-4311-abe3-f92f7891d805:502f1496-5885-4ecd-b38c-6d16f59ddfa6";
        String encode = Base64.encode(a);
        System.out.println(encode);
    }
}
