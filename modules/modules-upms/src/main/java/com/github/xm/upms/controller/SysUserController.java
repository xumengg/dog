package com.github.xm.upms.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.github.xm.common.util.Assert;
import com.github.xm.common.util.Query;
import com.github.xm.common.vo.UserVO;
import com.github.xm.upms.entity.SysUser;
import com.github.xm.upms.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import com.github.xm.common.controller.BaseController;

import java.util.Map;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-15
 */
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Autowired
    ISysUserService userService;

    @GetMapping("/{id:\\d+}")
    public SysUser getUserInfo(@PathVariable Integer id){
        return userService.selectById(id);
    }

    @RequestMapping("/userPage")
    public Page<UserVO> userPage(@RequestParam Map params, UserVO userVO){
        return userService.selectUserVOPage(new Query(params),userVO);
    }

    @GetMapping("/me")
    public  UserVO getCurrentUser(@AuthenticationPrincipal UserVO userVO){
        return userVO;
    }


}

