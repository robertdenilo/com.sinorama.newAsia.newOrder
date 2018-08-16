package com.sino.newasia.neworder.Service.Test2Service;


import com.sino.newasia.neworder.Entity.Test2;
import com.sino.newasia.neworder.Repository.Test2Repository.Test2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "tourServiceInt")
public class Test2Service implements Test2ServiceInt {
    @Autowired
    private Test2Repository test2Repository;

    @Cacheable(value="getTest2WithRedisCache", keyGenerator = "myKeyGenerator")
    public List<Test2> getTest2WithRedisCache(){
        return test2Repository.findAll();
    }
}
