package com.github.xm.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.xm.common.constants.CommomConstants;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import java.util.*;

/**
 * @author: XuMeng
 * @create: 2018/7/14 17:08
 * @description:
 **/
public class UserVO implements SocialUserDetails {


    private static final long serialVersionUID = -3304460049004094095L;

    /**
     * 主键ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    /**
     * 随机盐
     */
    private String salt;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 0-正常，1-删除 ,9-锁定
     */
    private String delFlag;
    /**
     * 简介
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门ID
     */
    private Integer deptId;
    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色列表
     */
    private List<RoleVO> roleList;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        roleList.stream().forEach(roleVO -> {
            String roleCode = roleVO.getRoleCode();
            list.add(new SimpleGrantedAuthority(roleCode));
        });
        //添加默认角色
        list.add(new SimpleGrantedAuthority(CommomConstants.DEFAULT_ROLE));
        return list;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return StringUtils.equals(delFlag,CommomConstants.LOCK)?false:true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return StringUtils.equals(delFlag,CommomConstants.NORMAL)?true:false;
    }

    @Override
   public String getUserId(){
        return String.valueOf(userId);
    };

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<RoleVO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleVO> roleList) {
        this.roleList = roleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        };
        if (o == null || getClass() != o.getClass()){
            return false;
        };
        UserVO userVO = (UserVO) o;
        return Objects.equals(userId, userVO.userId) &&
                Objects.equals(username, userVO.username) &&
                Objects.equals(password, userVO.password) &&
                Objects.equals(salt, userVO.salt) &&
                Objects.equals(createTime, userVO.createTime) &&
                Objects.equals(updateTime, userVO.updateTime) &&
                Objects.equals(delFlag, userVO.delFlag) &&
                Objects.equals(phone, userVO.phone) &&
                Objects.equals(avatar, userVO.avatar) &&
                Objects.equals(deptId, userVO.deptId) &&
                Objects.equals(deptName, userVO.deptName) &&
                Objects.equals(roleList, userVO.roleList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, username, password, salt, createTime, updateTime, delFlag, phone, avatar, deptId, deptName, roleList);
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag='" + delFlag + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
