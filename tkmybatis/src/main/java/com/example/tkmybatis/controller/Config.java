package com.example.tkmybatis.controller;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.validation.Validation;
import javax.validation.Validator;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyq
 * @time 2019/7/3 16:21
 */
@Configuration
public class Config {


    /**
     * spring mvc/RestTemplate/fegin Converters
     * @return
     */
    @Bean
    public HttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        return fastConverter;
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(@Autowired HttpMessageConverter fastJsonHttpMessageConverter) {
        return new HttpMessageConverters(fastJsonHttpMessageConverter);
    }

    /**
     * restTemplate bean
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {  return new RestTemplate();  }

    /**
     * 配置校验器
     * @return
     */
    @Bean
    public Validator validator(){
        return Validation.byProvider(HibernateValidator.class).configure()
                // 快速失败
                .failFast(true)
                .buildValidatorFactory().getValidator();
    }

}
