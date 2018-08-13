package com.github.xm.security.core.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xm.security.core.properties.DogSecurityProperties;
import com.github.xm.security.core.properties.LoginResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: XuMeng
 * @create: 2018/7/17 20:16
 * @description: 登陆成功处理
 **/
@Component("dogAuthenticationSuccessHandler")
@Slf4j
public class DogAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DogSecurityProperties dogSecurityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登陆成功");

        LoginResponseType loginResponseType = dogSecurityProperties.getBrower().getLoginResponseType();
        if(LoginResponseType.JSON.equals(loginResponseType)){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(objectMapper.writeValueAsString(authentication.getPrincipal()));
        }else {
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }

}
