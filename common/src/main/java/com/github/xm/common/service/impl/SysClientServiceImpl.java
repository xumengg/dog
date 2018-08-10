package com.github.xm.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.xm.common.constants.CommomConstants;
import com.github.xm.common.entity.SysClient;
import com.github.xm.common.mapper.SysClientMapper;
import com.github.xm.common.service.ISysClientService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端信息表 服务实现类
 * </p>
 *
 * @author XuMeng
 * @since 2018-07-28
 */
@Component
public class SysClientServiceImpl extends ServiceImpl<SysClientMapper, SysClient> implements ISysClientService, ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return selectOne(new EntityWrapper<SysClient>()
                .eq("client_uid", clientId)
                .eq("del_flag", CommomConstants.NORMAL));
    }
}
