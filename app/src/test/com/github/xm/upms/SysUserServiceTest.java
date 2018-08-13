package com.github.xm.upms;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.xm.common.service.impl.UserDetailsServiceImpl;
import com.github.xm.common.util.FormatJson;
import com.github.xm.common.util.Query;
import com.github.xm.common.vo.UserVO;
import com.github.xm.upms.entity.SysDept;
import com.github.xm.upms.entity.SysRole;
import com.github.xm.upms.entity.SysUser;
import com.github.xm.upms.entity.SysUserRole;
import com.github.xm.upms.service.impl.SysDeptServiceImpl;
import com.github.xm.upms.service.impl.SysRoleServiceImpl;
import com.github.xm.upms.service.impl.SysUserRoleServiceImpl;
import com.github.xm.upms.service.impl.SysUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: XuMeng
 * @create: 2018/7/15 22:53
 * @description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysUserServiceTest {

    @Autowired
    SysUserServiceImpl sysUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserRoleServiceImpl sysUserRoleService;

    @Autowired
    private SysRoleServiceImpl sysRoleService;

    @Autowired
    private SysDeptServiceImpl sysDeptService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    /**
     * @Transactional 注解事务可以不提交
     * @RollBack(true) 该注解已经过时不起作用了
     */
    @Test
    //@Transactional
    public void insertUserTest(){
        SysUser sysUser=new SysUser();
        sysUser.setUsername("李四");
        sysUser.setPhone("15927325469");
        String password="123456";
        sysUser.setDeptId(2);
        sysUser.setPassword(passwordEncoder.encode(password));
        boolean success = sysUserService.insert(sysUser);
        if(success){
            log.info("用户添加成功!");
        }

    }


    @Test
    public void insetUserRoleTest(){
        SysUser sysUser=new SysUser();
        sysUser.setUsername("徐猛");
        sysUser = sysUserService.selectOne(new EntityWrapper<SysUser>(sysUser));

        SysRole sysRole=new SysRole();
        sysRole.setRoleCode("ROLE_ADMIN");
        sysRole = sysRoleService.selectOne(new EntityWrapper<SysRole>(sysRole));

        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setUserId(sysUser.getUserId());
        sysUserRole.setRoleId(sysRole.getRoleId());
        sysUserRoleService.insert(sysUserRole);
    }


    @Test
    public void updateUserTest(){
        SysUser sysUser=new SysUser();
        sysUser.setUsername("徐猛");
        sysUser = sysUserService.selectOne(new EntityWrapper<SysUser>(sysUser));

        SysDept sysDept=new SysDept();
        sysDept.setDeptName("湖北总公司");
        sysDept=sysDeptService.selectOne(new EntityWrapper<SysDept>(sysDept));

        sysUser.setDeptId(sysDept.getDeptId());

        sysUserService.updateAllColumnById(sysUser);
    }

    @Test
    public void findByusernameTest(){
        UserDetails userDetails = userDetailsService.loadUserByUsername("徐猛");
        FormatJson.printConsole(userDetails);
    }

    @Test
    public  void selectUserVOPageTest(){
        Map params=new HashMap();
        Query query=new Query(params);
        Page<UserVO> userVOPage = sysUserService.selectUserVOPage(query);
        FormatJson.printConsole(userVOPage);
    }


    @Test
    public void selectUserPageTest(){
        Page<SysUser> sysUserPage = sysUserService.selectPage(new Query<SysUser>());
        FormatJson.printConsole(sysUserPage);
    }


    @Test
    public void deleteUserTest(){
        boolean success = sysUserService.deleteById(4);
        if(success){
            log.info("【张三】用户被删除了！");
        }
    }
}
