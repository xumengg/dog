package com.github.xm.security.core.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xm.common.util.ServerResponse;
import com.github.xm.security.core.properties.DogSecurityProperties;
import com.github.xm.security.core.properties.LoginResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: XuMeng
 * @create: 2018/7/17 20:44
 * @description:
 **/
@Component("dogAuthenticationFailureHandler")
public class DogAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DogSecurityProperties dogSecurityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        LoginResponseType loginResponseType = dogSecurityProperties.getBrower().getLoginResponseType();

        if(LoginResponseType.JSON.equals(loginResponseType)){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(objectMapper.writeValueAsString(ServerResponse.failure(exception)));
        }else {
            super.onAuthenticationFailure(request,response,exception);
        }

    }

}
