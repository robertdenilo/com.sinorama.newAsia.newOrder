package com.sino.newasia.neworder.Configuration;


//读取ap配置方法3
//@PropertySource("classpath:jdbc.properties")//如果是application.properties，就不用写@PropertyScource("application.properties")，其他名字用些   @Value("${jdbc.user}") private String user;
//@Configuration
//@EnableCaching
public class RedisCacheConfig_Obsoleted {

//    @Autowired
//    private Environment env;
//
//    public RedisCacheConfig_Obsoleted(){
//    }
//
//
//    @Bean(name="redisConnectionFactory")
//    public JedisConnectionFactory redisConnectionFactory() {
//        System.out.println("im in seriallizer for factory");
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        //读取ap配置方法1：String str1=ConfigurableApplicationContext.getEnvironment().getProperty("aaa");
//        //读取ap配置方法2：
//        jedisConnectionFactory.setUsePool(true);
//        jedisConnectionFactory.setHostName(env.getProperty("spring.redis.host"));
//        jedisConnectionFactory.setPort(env.getProperty("spring.redis.port",Integer.class));
//        jedisConnectionFactory.setPassword(env.getProperty("spring.redis.password"));
//
//
//        return jedisConnectionFactory;
//    }
//
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
//        System.out.println("im in seriallizer for cache mgr");
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofHours(1)); // 设置缓存有效期一小时
//        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory)).cacheDefaults(redisCacheConfiguration).build();
//
//        //  RedisTemplate<?,?> redisTemplate
///*        CacheManager cacheManager = new RedisCacheManager(redisTemplate, redisCacheConfiguration);
//        return cacheManager;*/
//
//    }
//    @Bean(name="redisTemplate")
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
//        System.out.println("im in seriallizer for temp");
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String,String>();
//        redisTemplate.setConnectionFactory(factory);
//        // key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
//        // 所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
//        // 或者JdkSerializationRedisSerializer序列化方式;
//        RedisSerializer<String> redisSerializer = new StringRedisSerializer();// Long类型不可以会出现异常信息;
//        redisTemplate.setKeySerializer(redisSerializer);
//        redisTemplate.setHashKeySerializer(redisSerializer);
//        return redisTemplate;
//    }


}
