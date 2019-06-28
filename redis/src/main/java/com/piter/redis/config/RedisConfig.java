package com.piter.redis.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;
import java.util.concurrent.Executor;

/**
 * @time 2019/5/9 21:33
 */
@Configuration
@Slf4j
public class RedisConfig {

    @Autowired
    private ApplicationContext applicationContext;
    /**
     * 配置RedisTemplate序列化
     */
    @Bean
    RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        //使用FastJsonRedisSerializer来序列化redis的value值,但是有个缺点，取出来的时候需要手动转换成对象
        //FastJsonRedisSerializer jsonSerializer = new FastJsonRedisSerializer(Object.class);
        //使用GenericFastJsonRedisSerializer来序列化redis的value值
        GenericFastJsonRedisSerializer jsonSerializer = new GenericFastJsonRedisSerializer();

        //使用Jackson2JsonRedisSerialize来序列化和反序列化redis的value值
//        Jackson2JsonRedisSerializer jsonSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jsonSerializer.setObjectMapper(objectMapper);

        RedisSerializer<String> stringSerializer = template.getStringSerializer();

        //使用stringSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(jsonSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(jsonSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
