package com.sino.newasia.neworder.Configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    /**
     * 缓存对象集合中，缓存是以 key-value 形式保存的。当不指定缓存的 key 时，SpringBoot 会使用 SimpleKeyGenerator 生成 key。
     * @return
     */
    @Bean
    public KeyGenerator myKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };

    }

    @Autowired
    private Environment env;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        System.out.println("im in seriallizer JedisConnectionFactory xxxxxxxxxxxxxxxx");
        //读取ap配置方法1：String str1=ConfigurableApplicationContext.getEnvironment().getProperty("aaa");
        //读取ap配置方法2：
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setHostName(env.getProperty("spring.redis.host"));
        jedisConnectionFactory.setPort(env.getProperty("spring.redis.port",Integer.class));
        jedisConnectionFactory.setPassword(env.getProperty("spring.redis.password"));


        return jedisConnectionFactory;
    }


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        System.out.println("im in seriallizer cacheManager xxxxxxxxxxxxxxxx");
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1)); // 设置缓存有效期一小时
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory)).cacheDefaults(redisCacheConfiguration).build();
    }

    @Bean(name="redisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        System.out.println("im in seriallizer Redis Config xxxxxxxxxxxxxxxx");
        StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }


//    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
//        return new RedisCacheManager(redisTemplate);
//    }


//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate template = new StringRedisTemplate(factory);
//        @SuppressWarnings({ "rawtypes", "unchecked" })
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();
//        return template;
//    }
}
