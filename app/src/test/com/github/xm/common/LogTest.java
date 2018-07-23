package com.github.xm.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: XuMeng
 * @create: 2018/7/20 16:20
 * @description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {

    @Test
    public void logTest(){
        log.info("info log test");
        log.error("error log test");
        log.debug("debug log test");
    }

}
