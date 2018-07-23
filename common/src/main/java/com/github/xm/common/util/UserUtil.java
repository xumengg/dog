package com.github.xm.common.util;

import com.github.xm.common.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ListUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: XuMeng
 * @create: 2018/7/18 17:34
 * @description:
 **/
@Slf4j
public class UserUtil {


    public static UserVO currentUser(){
        try {
            return  (UserVO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (NullPointerException e) {

        }
        return null;
    }

    public static Integer currentUserDeptId(){
        UserVO userVO = currentUser();
        return userVO.getDeptId();
    }

    public static List<Integer> currentUserRoleIds(){
        UserVO userVO = currentUser();
        List<Integer> currentUserRoleIds = userVO.getRoleList().stream().map(roleVO -> {
            return roleVO.getRoleId();
        }).collect(Collectors.toList());
        return currentUserRoleIds;
    }

}
