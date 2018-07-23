package com.github.xm.security.core.social.support;

import com.github.xm.security.core.authentication.handler.DogAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author: XuMeng
 * @create: 2018/7/22 17:21
 * @description:
 **/
public class DogSpringSocialConfigurer  extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    private AuthenticationSuccessHandler successHandler;


    public DogSpringSocialConfigurer(String filterProcessesUrl, AuthenticationSuccessHandler successHandler) {
        this.filterProcessesUrl = filterProcessesUrl;
        this.successHandler = successHandler;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        filter.setAuthenticationSuccessHandler(successHandler);
        return (T) filter;
    }

}
