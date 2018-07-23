package com.github.xm.security.core.config;

import com.github.xm.security.core.properties.DogSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: XuMeng
 * @create: 2018/7/16 23:08
 * @description:  使SecurityProperties生效
 **/
@Configuration
@EnableConfigurationProperties(DogSecurityProperties.class)
public class DogSecurityConstantsConfig {

}
