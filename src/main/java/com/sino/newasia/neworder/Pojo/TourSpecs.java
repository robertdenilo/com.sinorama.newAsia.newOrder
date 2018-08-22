package com.sino.newasia.neworder.Pojo;

import com.sino.newasia.neworder.Entity.Tour;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TourSpecs {

    public static Specification<Tour> getTourSpec(){
        return new Specification<Tour>(){
            @Override
            public Predicate toPredicate(Root<Tour> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("routeid"),"BHHSB-14-2019");
            }
        };
    }

}
