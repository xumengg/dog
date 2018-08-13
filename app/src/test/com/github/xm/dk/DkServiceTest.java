package com.github.xm.dk;

import com.github.xm.dk.service.DkService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: XuMeng
 * @create: 2018/8/4 17:44
 * @description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DkServiceTest {

    @Autowired
    private DkService dkServiceFilterExample;

    @Test
    public void testDkServiceFilterExample(){
        dkServiceFilterExample.filterChainExample();
    }
}
