package com.github.xm.security.brower.filter;

import com.github.xm.security.core.constants.DogSecurityConstants;
import com.github.xm.common.exception.ValidateCodeException;
import com.github.xm.security.core.properties.ImageCodeProperties;
import com.github.xm.security.core.validate.code.filter.AbstractValidateCodeFilter;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: XuMeng
 * @create: 2018/7/18 10:24
 * @description:
 **/
public class ImageValidateCodeFilter extends AbstractValidateCodeFilter {


    @Override
    protected void initUrls() {
        ImageCodeProperties image = getSecurityProperties().getCode().getImage();
        String[] split = StrUtil.split(image.getUrl(), ",");
        for (String url:split) {
            urls.add(url);
        }
        urls.add("/authentication/form");
    }

    @Override
    protected void validate(HttpServletRequest request) throws ServletRequestBindingException {

        String inputCode = ServletRequestUtils.getStringParameter(request, "imageCode");
        String imgCodeKey = ServletRequestUtils.getStringParameter(request, "imgCodeKey");

        String  cacheCoke = (String)getRedisTemplate().opsForValue().get(DogSecurityConstants.DEFAULT_CODE_KEY+imgCodeKey);

        if(StrUtil.isEmpty(inputCode)){
            throw new ValidateCodeException("输入验证码不正确");
        }

        if(StrUtil.isEmpty(cacheCoke)){
            throw new ValidateCodeException("验证码已失效");
        }

        if (!StrUtil.equals(inputCode,cacheCoke)) {
            throw new ValidateCodeException("验证码输入错误");
        }
    }

}
