package com.github.xm.common.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: XuMeng
 * @create: 2018/7/16 22:21
 * @description:
 **/
@Data
public class ServerResponse<T> implements Serializable {
    private static final long serialVersionUID = -4965306581763845706L;

    private static int SUCCESS_CODE = 1;
    private static int FAILURE_CODE = 0;

    private Integer code;
    private T data;
    private String msg;

    private ServerResponse(Integer code,T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static ServerResponse success(){
        return new ServerResponse(SUCCESS_CODE,null,"success");
    }

    public static <T> ServerResponse successWithData(T data){
        return new ServerResponse(SUCCESS_CODE,data,"success");
    }

    public static ServerResponse failure(Throwable throwable){
        return new ServerResponse(FAILURE_CODE,null,throwable.getMessage());
    }

    public static ServerResponse failure(String  msg){
        return new ServerResponse(FAILURE_CODE,null,msg);
    }


}
