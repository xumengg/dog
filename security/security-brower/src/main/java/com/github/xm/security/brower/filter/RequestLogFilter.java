package com.github.xm.security.brower.filter;

import com.github.xm.common.sender.ILogVOSender;
import com.github.xm.common.util.UserUtil;
import com.github.xm.common.vo.LogVO;
import com.github.xm.common.vo.UserVO;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.util.URLUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: XuMeng
 * @create: 2018/7/19 13:34
 * @description:
 **/
public class RequestLogFilter extends OncePerRequestFilter {

    private ILogVOSender logSendServcie;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            LogVO log=new LogVO();
            log.setRequestUri(URLUtil.getPath(request.getRequestURI()));
            log.setMethod(request.getMethod());
            log.setRemoteAddr(HttpUtil.getClientIP(request));
            log.setUserAgent(request.getHeader("user-agent"));
            log.setParams(HttpUtil.toParams(request.getParameterMap()));

            UserVO userVO = UserUtil.currentUser();
            if(userVO != null){
                log.setCreateBy(userVO.getUsername());
            }
            logSendServcie.sendLogVO(log);
        }finally {
            filterChain.doFilter(request,response);
        }

    }

    public void setLogSendServcie(ILogVOSender logSendServcie) {
        this.logSendServcie = logSendServcie;
    }
}
