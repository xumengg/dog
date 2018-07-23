package com.github.xm.common.util;

import com.github.xm.common.exception.CheckException;
import org.apache.commons.lang.StringUtils;

/**
 * @author: XuMeng
 * @create: 2018/7/15 12:47
 * @description:
 **/
public class Assert {

    public static void isNotNull(Object o,String msg){
        if (null == o) {
            throw new CheckException(msg);
        }
    }


    public static  void isNotBlank(String str,String msg){
        if (StringUtils.isEmpty(str)) {
            throw new CheckException(msg);
        }
    }
}
