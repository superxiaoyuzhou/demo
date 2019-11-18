package com.chinaums.dataencrypt.config;

import com.chinaums.dataencrypt.interceptor.DataEncryptInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据加密自动配置
 * @author lyq
 * @time 2019/9/26 10:27
 */
@Configuration
public class DataEncryptAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(DataEncryptInterceptor.class)
    public DataEncryptInterceptor dataEncryptInterceptor(){
        return new DataEncryptInterceptor();
    }

}
