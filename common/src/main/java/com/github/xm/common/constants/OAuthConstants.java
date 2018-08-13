package com.github.xm.common.constants;

/**
 * @author: XuMeng
 * @create: 2018/7/28 21:36
 * @description:
 **/
public interface OAuthConstants {
    /**
     * token有效期
     */
   int  ACCESS_TOKEN_VALIDITY_SECONDS = 60*60*24;

    /**
     * refresh token 有效期
     */
   int REFRESH_TOKEN_VALIDITY_SECONDS = 60*60*24*30;

}
