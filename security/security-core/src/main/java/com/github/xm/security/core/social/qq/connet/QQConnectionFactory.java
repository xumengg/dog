package com.github.xm.security.core.social.qq.connet;

import com.github.xm.security.core.social.qq.adapter.QQApiAdapter;
import com.github.xm.security.core.social.qq.api.QQ;
import com.github.xm.security.core.social.qq.provider.QQServiceProvider;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author: XuMeng
 * @create: 2018/7/22 15:01
 * @description:
 **/
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId,String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQApiAdapter());
    }
}
