package com.github.xm.security.core.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xm.common.util.ServerResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: XuMeng
 * @create: 2018/7/26 21:10
 * @description:
 **/
public class DogLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ObjectMapper objectMapper=new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ServerResponse.successWithData("退出成功!")));
    }
}
