package com.github.xm.security.core.constants;

/**
 * @author: XuMeng
 * @create: 2018/7/14 21:48
 * @description:
 **/
public interface DogSecurityConstants {


    /**
     *  前台提交给后台用户名字段的key
     */
    String DEFAULT_USERNAME_KEY = "username";

    /**
     * QQ用户转为系统用户时候的名字前缀
     */
    String QQ_USERNAME_KEY = "qq_";

    /**
     * 登陆页面url
     */
    String LOGIN_PAGE = "/authentication/require";

    /**
     * 处理登陆请求url
     */
    String LOGIN_PROESSING_URL = "/authentication/form";

    //---------------------------------短信验证码相关-------------------------------------

    /**
     * 默认的手机验证码登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_PHONE = "/authentication/phone";


    /**
     * 默认保存短信验证码的前缀
     */
    String DEFAULT_SMS_KEY = "DEFAULT_SMS_KEY";

    /**
     * 默认保存短信验证码过期时间
     */
    int DEFAULT_SMS_EXPIRE = 60;


   //--------------------------------图片验证码相关常量-----------------------------------

    /**
     * 默认保存图片验证码的前缀
     */
    String DEFAULT_CODE_KEY ="DEFAULT_CODE_KEY";

    /**
     * 默认生成图形验证码过期时间
     */
    int DEFAULT_IMAGE_EXPIRE = 60;

    /**
     * 图片边框
     */
    String DEFAULT_IMAGE_BORDER = "no";
    /**
     * 默认图片间隔
     */
    String DEFAULT_CHAR_SPACE = "5";

    /**
     * 边框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue.
     */
    String DEFAULT_COLOR_FONT = "black";

    /**
     * 默认生成图形验证码宽度
     */
    String DEFAULT_IMAGE_WIDTH = "100";

    /**
     * 默认生成图像验证码高度
     */
    String DEFAULT_IMAGE_HEIGHT = "40";

    /**
     * 默认生成图形验证码长度
     */
    String DEFAULT_IMAGE_LENGTH = "4";

    /**
     * 验证码文字大小
     */
    String DEFAULT_IMAGE_FONT_SIZE = "30";


}
