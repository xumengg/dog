package com.github.xm.upms;

import com.github.xm.upms.entity.SysRole;
import com.github.xm.upms.service.impl.SysRoleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: XuMeng
 * @create: 2018/7/16 11:03
 * @description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleServiceTest {

    @Autowired
    private SysRoleServiceImpl sysRoleService;

    @Test
    public void insertRoleTest(){
        //INSERT INTO `sys_role` VALUES
        // ('1', 'admin', 'ROLE_ADMIN', '超级管理员', '2017-10-29 15:45:51', '2018-04-22 11:40:29', '0'),
        // ('14', 'demo', 'demo', 'demo用户', '2018-04-20 07:14:32', '2018-04-21 23:35:22', '0');

        //`role_id` int(11) NOT NULL AUTO_INCREMENT,
        //  `role_name` varchar(64) COLLATE utf8mb4_bin NOT NULL,
        //  `role_code` varchar(64) COLLATE utf8mb4_bin NOT NULL,
        //  `role_desc` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
        //  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        //  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
        //  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',

        SysRole sysRole=new SysRole();
        sysRole.setRoleName("admin");
        sysRole.setRoleCode("ROLE_ADMIN");
        sysRole.setRoleDesc("超级管理员");
        sysRoleService.insert(sysRole);
    }
}
