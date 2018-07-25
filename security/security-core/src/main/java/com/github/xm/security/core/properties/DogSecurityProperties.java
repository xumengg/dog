package com.github.xm.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: XuMeng
 * @create: 2018/7/16 23:08
 * @description:
 **/
@ConfigurationProperties(prefix = "dog.security")
@Data
public class DogSecurityProperties {

    private BrowerPropertes brower  = new BrowerPropertes();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();

    private SessionProperties session=new SessionProperties();
}
