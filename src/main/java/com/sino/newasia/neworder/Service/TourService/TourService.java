package com.sino.newasia.neworder.Service.TourService;


import com.sino.newasia.neworder.Entity.Tour;
import com.sino.newasia.neworder.Repository.TourRepository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "tourServiceInt")
public class TourService implements  TourServiceInt{
    @Autowired
    private TourRepository tourRepository;

    @Cacheable(value="getTourWithRedisCache", keyGenerator = "myKeyGenerator")
    public List<Tour> getTourWithRedisCache(){
        return tourRepository.findAll();
    }
}
