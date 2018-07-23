package com.github.xm.security.core.validate.code.filter;

import com.github.xm.common.exception.ValidateCodeException;
import com.github.xm.common.service.impl.UserDetailsServiceImpl;
import com.github.xm.security.core.authentication.handler.DogAuthenticationFailureHandler;
import com.github.xm.security.core.properties.DogSecurityProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: XuMeng
 * @create: 2018/7/21 16:06
 * @description:
 **/
abstract public class AbstractValidateCodeFilter  extends OncePerRequestFilter {

    private RedisTemplate redisTemplate;

    private DogAuthenticationFailureHandler authenticationFailureHandler;

    private DogSecurityProperties securityProperties;

    protected Set<String> urls = new HashSet<>();

    protected AntPathMatcher pathMatcher = new AntPathMatcher();

    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void initFilterBean() throws ServletException {
        initUrls();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean action = false;
        for (String url:urls) {
            if (pathMatcher.match(url,request.getRequestURI())) {
                action=true;
            }
        }

        /**
         * 处理登陆请求
         */
        if (action) {
            try {
                validate(request);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }

        filterChain.doFilter(request,response);
    }

    protected abstract void initUrls();


    /**
     * 子类覆盖具体校验逻辑
     * @param request
     */
    protected abstract void validate(HttpServletRequest request)  throws ServletRequestBindingException;


    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public DogAuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(DogAuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public DogSecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(DogSecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public UserDetailsServiceImpl getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
