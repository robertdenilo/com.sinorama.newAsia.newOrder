package com.sino.newasia.neworder.Service.TourService;

import com.sino.newasia.neworder.Entity.Tour;
import com.sino.newasia.neworder.Repository.TourRepository.TourRepository;
import org.hibernate.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "tourCache")
@Service
public class TourServiceCached {
    @Autowired
    private TourRepository tourRepository;

    @CacheEvict(key="'tourCache'")
    public Tour save(Tour tour) throws Exception {
        return tourRepository.save(tour);
    }

    @CachePut(key = "'tour_'+#tour.getTourid()")
    public Tour update(Tour tour) throws CacheException {
        Tour tourinfo = tourRepository.findByTourid(tour.getTourid());
        if (null == tourinfo){
            throw new  CacheException("Not Find");
        }
        tourinfo.setStatus(tour.getStatus());
        tourinfo.setDepartdate(tour.getDepartdate());
        tourRepository.update(tourinfo);
        return tourinfo;
    }

    @Cacheable(key="'tour_'+#tourid")
    public Tour findByTourid(String tourid){
        System.err.println("没有走缓存！"+tourid);
        Tour tt = (Tour)tourRepository.findByTourid(tourid);
        return tt;
    }

    @CacheEvict(key = "'tour_'+#tourid")//这是清除缓存
    public void delete(String tourid){
        Tour tour = this.findByTourid(tourid);
        tourRepository.delete(tour);
    }
}
