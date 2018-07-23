package com.github.xm.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: XuMeng
 * @create: 2018/7/16 9:48
 * @description:
 **/
@Slf4j
public class FormatJson {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static String print(Object o) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printConsole(Object o){
        log.info(print(o));
    }

}
