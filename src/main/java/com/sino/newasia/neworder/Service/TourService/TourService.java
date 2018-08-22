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







    @Override
    public Page<Tour> findTourNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        return tourRepository.findAll(pageable);
    }

//    @Override
//    public Page<Book> findTourCriteria(Integer page, Integer size, final BookQuery bookQuery) {
//        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
//        Page<Book> bookPage = bookRepository.findAll(new Specification<Book>(){
//            @Override
//            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> list = new ArrayList<Predicate>();
//                if(null!=bookQuery.getName()&&!"".equals(bookQuery.getName())){
//                    list.add(criteriaBuilder.equal(root.get("name").as(String.class), bookQuery.getName()));
//                }
//                if(null!=bookQuery.getIsbn()&&!"".equals(bookQuery.getIsbn())){
//                    list.add(criteriaBuilder.equal(root.get("isbn").as(String.class), bookQuery.getIsbn()));
//                }
//                if(null!=bookQuery.getAuthor()&&!"".equals(bookQuery.getAuthor())){
//                    list.add(criteriaBuilder.equal(root.get("author").as(String.class), bookQuery.getAuthor()));
//                }
//                Predicate[] p = new Predicate[list.size()];
//                return criteriaBuilder.and(list.toArray(p));
//            }
//        },pageable);
//        return bookPage;
//    }





}
