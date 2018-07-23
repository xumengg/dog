package com.github.xm.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author: XuMeng
 * @create: 2018/7/22 15:15
 * @description:
 **/
@Data
public class QQProperties  extends SocialProperties {

    private String providerId = "qq";

}
