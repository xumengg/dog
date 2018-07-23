package com.github.xm.security.core.validate.code.filter;

import com.github.xm.security.core.constants.DogSecurityConstants;
import com.github.xm.common.exception.ValidateCodeException;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: XuMeng
 * @create: 2018/7/21 0:34
 * @description:
 **/
public class SmsValidateCodeFilter extends AbstractValidateCodeFilter {



    @Override
    protected void initUrls() {
        String smsCodeInterceptUrls = getSecurityProperties().getCode().getSms().getUrl();
        String[] split = StrUtil.split(smsCodeInterceptUrls, ",");
        for (String url:split) {
            urls.add(url);
        }
        urls.add(DogSecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_PHONE);
    }

    @Override
    protected void validate(HttpServletRequest request) throws ServletRequestBindingException {

        String phone = ServletRequestUtils.getStringParameter(request, "phone");
        String inputSmsCode = ServletRequestUtils.getStringParameter(request, "smsCode");

        String  cacheSmsCoke = (String)getRedisTemplate().opsForValue().get(DogSecurityConstants.DEFAULT_SMS_KEY+phone);

        if(StrUtil.isEmpty(phone)){
            throw new ValidateCodeException("手机号不能为空");
        }

        if(StrUtil.isEmpty(cacheSmsCoke)){
            throw new ValidateCodeException("验证码已失效");
        }

        if (!StrUtil.equals(inputSmsCode,cacheSmsCoke)) {
            throw new ValidateCodeException("验证码输入错误");
        }

        UserDetails userDetails = getUserDetailsService().loadUserByPhone(phone);
        if(userDetails == null){
            throw new ValidateCodeException("无效手机号");
        }

        /**
         * 验证通过  将用户名绑定到请求上
         */
        request.setAttribute("username",userDetails.getUsername());
    }


}