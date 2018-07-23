package com.github.xm.common.config;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.github.xm.common.interceptor.DataScopeInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author: XuMeng
 * @create: 2018/7/15 23:42
 * @description:
 **/
@Configuration
@MapperScan({"com.github.xm.common.mapper","com.github.xm.upms.mapper"})
public class MyBatisConfig {

    @Bean("mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource,GlobalConfiguration globalConfiguration) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("com.github.xm.upms.entity");
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{
                new PaginationInterceptor(),
                new DataScopeInterceptor()

        });

        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*.xml");
        sqlSessionFactory.setMapperLocations(resources);
        sqlSessionFactory.setGlobalConfig(globalConfiguration);
        return sqlSessionFactory.getObject();
    }

    /**
     * #mybaits-plus配置，修改主键类型，mapper.xml、type 别名等
     * mybatis-plus:
     *   mapper-locations: classpath:/mapper/*Mapper.xml
     *   typeAliasesPackage: com.github.xm.upms.entity
     *   global-config:
     *     #主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
     *     id-type: 0
     *     #字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
     *     field-strategy: 1
     *     #驼峰下划线转换
     *     db-column-underline: true
     *     #刷新mapper 调试神器
     *     refresh-mapper: true
     *     #数据库大写下划线转换
     *     #capital-mode: true
     *   configuration:
     *     map-underscore-to-camel-case: true
     *     cache-enabled: true
     * @return
     */
    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration();
        conf.setIdType(0);
        conf.setFieldStrategy(1);
        conf.setDbColumnUnderline(true);
        conf.setRefresh(true);
        conf.setCapitalMode(true);
        return conf;
    }
}
