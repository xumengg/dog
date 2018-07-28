package com.github.xm.common.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.*;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.github.xm.common.constants.OAuthConstants;
import com.xiaoleilu.hutool.collection.CollUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.io.Serializable;

/**
 * <p>
 * 客户端信息表
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-28
 */
@TableName("sys_client")
public class SysClient extends Model<SysClient> implements ClientDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "client_id", type = IdType.AUTO)
    private Integer clientId;
    /**
     * 客户端的uuid
     */
    @TableField("client_uid")
    private String clientUid;
    /**
     * 客户端密码
     */
    @TableField("client_secret")
    private String clientSecret;
    /**
     * 1:小程序  2:android   3:ios
     */
    @TableField("client_type")
    private String clientType;
    /**
     * 0:正常 1:删除
     */
    @TableField("del_flag")
    private String delFlag;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    /**
     *  覆写ClientDetails父类方法 返回客户端uid  而不是数据库的主键
     * @return
     */
    @Override
    public String getClientId() {
        return clientUid;
    }

    @Override
    public Set<String> getResourceIds() {
        return  Collections.emptySet();
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientUid() {
        return clientUid;
    }

    public void setClientUid(String clientUid) {
        this.clientUid = clientUid;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return Collections.emptySet();
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return CollUtil.newHashSet("password");
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Collections.EMPTY_SET;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return CollUtil.newArrayList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return OAuthConstants.ACCESS_TOKEN_VALIDITY_SECONDS;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return OAuthConstants.REFRESH_TOKEN_VALIDITY_SECONDS;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.EMPTY_MAP;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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

    @Override
    protected Serializable pkVal() {
        return this.clientId;
    }

    @Override
    public String toString() {
        return "SysClient{" +
        ", clientId=" + clientId +
        ", clientUid=" + clientUid +
        ", clientSecret=" + clientSecret +
        ", clientType=" + clientType +
        ", delFlag=" + delFlag +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
