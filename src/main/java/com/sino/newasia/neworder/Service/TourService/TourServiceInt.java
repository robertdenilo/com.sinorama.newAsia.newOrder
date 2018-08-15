package com.sino.newasia.neworder.Service.TourService;

import com.sino.newasia.neworder.Entity.Tour;

import java.util.List;

public interface TourServiceInt {

    public List<Tour> getTourWithRedisCache();
}
