package com.github.xm.upms;

import com.github.xm.common.util.FormatJson;
import com.github.xm.upms.entity.SysDept;
import com.github.xm.upms.service.impl.SysDeptServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @author: XuMeng
 * @create: 2018/7/16 11:11
 * @description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysDeptServiceTest {

    @Autowired
    private SysDeptServiceImpl sysDeptService;

    @Test
    public void insertDeptTest(){
        SysDept sysDept=new SysDept();
        sysDept.setDeptName("武昌分公司");
        sysDept.setOrderNum(0);
        sysDept.setParentId(2);
        //  `dept_id` int(20) NOT NULL AUTO_INCREMENT,
        //  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
        //  `order_num` int(11) DEFAULT NULL COMMENT '排序',
        //  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
        //  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
        //  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
        //  `parent_id` int(11) DEFAULT NULL,

        //INSERT INTO `sys_dept` VALUES
        // ('1', '山东农信', null, '2018-01-22 19:00:23', '2018-01-23 12:40:46', '0', '0'),
        sysDeptService.insert(sysDept);
    }


    @Test
    public void getChildrenTest(){
        Set<Integer> children = sysDeptService.getChildren(-1);
        FormatJson.printConsole(children);
    }

    @Test
    public void getTreeNodeTest(){
     log.info(FormatJson.print(sysDeptService.getTreeNode(1)));
    }

}
