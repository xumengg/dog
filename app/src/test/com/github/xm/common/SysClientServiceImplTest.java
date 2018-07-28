package com.github.xm.common;

import com.github.xm.common.constants.ClientTypeEnum;
import com.github.xm.common.entity.SysClient;
import com.github.xm.common.service.impl.SysClientServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author: XuMeng
 * @create: 2018/7/28 21:52
 * @description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysClientServiceImplTest {

    @Autowired
    private SysClientServiceImpl sysClientService;

    @Test
    public void insertTest(){
        SysClient sysClient=new SysClient();
        sysClient.setClientUid(UUID.randomUUID().toString());
        sysClient.setClientSecret(UUID.randomUUID().toString());
        sysClient.setClientType(ClientTypeEnum.applet.code);
        sysClientService.insert(sysClient);
    }
}
