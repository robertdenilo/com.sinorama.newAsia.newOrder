package com.sino.newasia.neworder.Service.TourService;

import com.sino.newasia.neworder.Entity.Tour;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TourServiceInt {

    public List<Tour> getTourWithRedisCache();

    Page<Tour> findTourNoCriteria(Integer page, Integer size);
    //Page<Tour> findTourCriteria(Integer page,Integer size,TourQuery bookQuery);
}
