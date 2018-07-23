package com.github.xm.security.core.social.qq.provider;

import com.github.xm.security.core.social.qq.api.QQ;
import com.github.xm.security.core.social.qq.api.QQimpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author: XuMeng
 * @create: 2018/7/22 12:07
 * @description:
 **/
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private String appSecret;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId,String appSecret) {
        //String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl
        super(new QQOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId=appId;
        this.appSecret=appSecret;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQimpl(accessToken,appId);
    }
}
