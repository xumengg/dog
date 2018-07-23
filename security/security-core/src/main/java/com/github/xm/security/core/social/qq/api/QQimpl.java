package com.github.xm.security.core.social.qq.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xm.common.util.FormatJson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author: XuMeng
 * @create: 2018/7/22 11:36
 * @description:
 **/
@Slf4j
public class QQimpl  extends AbstractOAuth2ApiBinding  implements QQ {

    /**
     * 获取opendId
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取用户信息
     */
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    String openId;

    String appId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQimpl(String accessToken,String appId) {

        /**
         * token策略 将token放到请求参数 而不是请求头中
         */
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
        String GetOpenIdUrl=String.format(URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(GetOpenIdUrl, String.class);

        this.appId = appId;
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
        log.info("获取到QQ用户的openid为【{}】",openId);
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);

        log.info("获取到QQ用户的信息为【{}】",result);

        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }



}
