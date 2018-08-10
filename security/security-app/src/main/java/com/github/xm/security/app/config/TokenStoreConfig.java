package com.github.xm.security.app.config;

import com.github.xm.security.core.constants.DogSecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author: XuMeng
 * @create: 2018/7/30 21:49
 * @description:
 **/
@Configuration
public class TokenStoreConfig {

    @Configuration
    @ConditionalOnProperty(prefix = "dog.security.oauth2", name = "tokenStore", havingValue = "redis")
    public static class  RedisTokenStoreConfig{

        @Autowired
        private RedisConnectionFactory redisConnectionFactory;

        @Bean
        public TokenStore tokenStore(){
            return new RedisTokenStore(redisConnectionFactory);
        }
    }


    @Configuration
    @ConditionalOnProperty(prefix = "dog.security.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
    public static class JwtConfig {

        @Bean
        public TokenStore tokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            jwtAccessTokenConverter.setSigningKey(DogSecurityConstants.SIGNING_KEY);
            return jwtAccessTokenConverter;
        }

    }


}
