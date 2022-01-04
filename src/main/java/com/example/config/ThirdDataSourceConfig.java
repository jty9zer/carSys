package com.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 车辆配件数据库配置类（MySQL)
 *
 * @Author Zhang Chenyang
 * @Date 2022/01/04 20:43
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = "com.example.dao.third", sqlSessionTemplateRef = "thirdSqlSessionTemplate")
public class ThirdDataSourceConfig {
    // 获取映射文件所在的路径
    @Value("${mybatis.third.mapper-locations}")
    private String thirdMapperPath;

    /**
     * @return javax.sql.DataSource
     * @Author jason.tang
     * @Description: 根据配置文件，注入数据源
     * @Date: 15:53 2019/2/13
     * @Param []
     */
    @Bean(name = "thirdDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.third")
    public DataSource thirdDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * @return org.apache.ibatis.session.SqlSessionFactory
     * @Author jason.tang
     * @Description: 注入SqlSessionFactory，指定数据源和映射文件路径
     * @Date: 15:54 2019/2/13
     * @Param [dataSource]
     */
    @Bean(name = "thirdSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("thirdDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(thirdMapperPath);
        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * @return org.springframework.jdbc.datasource.DataSourceTransactionManager
     * @Author jason.tang
     * @Description: 注入DataSourceTransactionManager事物管理器
     * @Date: 15:55 2019/2/13
     * @Param [dataSource]
     */
    @Bean(name = "thirdTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("thirdDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * @return org.mybatis.spring.SqlSessionTemplate
     * @Author jason.tang
     * @Description: 注入SqlSessionTemplate模板
     * @Date: 15:55 2019/2/13
     * @Param [sqlSessionFactory]
     */
    @Bean(name = "thirdSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("thirdSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
