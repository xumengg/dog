package com.github.xm.security.brower.controller;

import com.github.xm.common.util.ServerResponse;
import com.github.xm.security.core.constants.DogSecurityConstants;
import com.github.xm.security.core.properties.DogSecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: XuMeng
 * @create: 2018/7/16 21:56
 * @description:
 **/
@RestController
public class BrowerSecurityController {

    private RequestCache requestCache=new HttpSessionRequestCache();

    /**
     * 重定向辅助类
     */
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    @Autowired
    private DogSecurityProperties dogSecurityProperties;

    @RequestMapping(DogSecurityConstants.LOGIN_PAGE)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ServerResponse<String> requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null){
            String redirectUrl = savedRequest.getRedirectUrl();
            if(StringUtils.endsWith(redirectUrl,".html")){
                redirectStrategy.sendRedirect(request,response, dogSecurityProperties.getBrower().getLoginPage());
            }
        }
        return ServerResponse.failure("访问服务需要身份认证，请引导用户到登陆页");
    }
}
