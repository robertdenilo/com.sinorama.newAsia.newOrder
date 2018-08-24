package com.sino.newasia.neworder.Service.TourService;


import com.sino.newasia.neworder.Entity.Tour;
import com.sino.newasia.neworder.Repository.TourRepository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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



    @Override
    public Page<Tour> findTourNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "departdate");
        return tourRepository.findAll(pageable);
    }

    @Override
    public Page<Tour> findTourCriteria(Integer page, Integer size, final Tour tour, Pageable pageable) {
        //Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "departdate");
        Page<Tour> tourPage = tourRepository.findAll(new Specification<Tour>(){
            @Override
            public Predicate toPredicate(Root<Tour> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(null!=tour.getRouteid()&&!"".equals(tour.getRouteid())){
                    list.add(criteriaBuilder.equal(root.get("routeid").as(String.class), tour.getRouteid()));
                }
                if(null!=tour.getRouteid()&&!"".equals(tour.getRouteid())){
                    list.add(criteriaBuilder.greaterThan(root.get("departdate").as(String.class), tour.getDepartdate()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        return tourPage;
    }

    public void save(Tour tour){
        tourRepository.save(tour);
    }



}
