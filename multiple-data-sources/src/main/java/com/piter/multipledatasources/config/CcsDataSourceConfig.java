package com.piter.multipledatasources.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.chinaums.dataencrypt.interceptor.DataEncryptInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author lyq
 * @time 2019/5/14 21:00
 */
@Configuration
@MapperScan(basePackages = "com.com.piter.multipledatasources.mapper.ccs",sqlSessionTemplateRef = "ccsSqlSessionTemplate")
public class CcsDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.ccs")
    public DataSourceProperties ccsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.ccs.hikari")
    public HikariDataSource ccsDataSource() {
        return ccsDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean
    public SqlSessionFactory ccsSqlSessionFactory(@Qualifier("ccsDataSource") DataSource dataSource, DataEncryptInterceptor interceptor) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/ccs/*.xml"));
        Interceptor[] interceptors = {interceptor};
        bean.setPlugins(interceptors);
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager ccsTransactionManager(@Qualifier("ccsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate ccsSqlSessionTemplate(@Qualifier("ccsSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
