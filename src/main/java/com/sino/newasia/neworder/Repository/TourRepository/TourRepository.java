package com.sino.newasia.neworder.Repository.TourRepository;

import com.sino.newasia.neworder.Entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//import org.springframework.data.repository.query.Param;

public interface TourRepository extends JpaRepository<Tour, Integer>, JpaSpecificationExecutor<Tour> {


    public List<Tour> findAllByTourid(String tourid);
    public Tour findByTourid(String tourid);

    @Query(value = "select * from t_tour  t where t.status = 1 and t.routeid = :routeid order by t.departdate", nativeQuery = true)
    public List<Object[]>  findAllByRouteid(@Param("routeid") String routeid);


    public List<Object[]> doTest();

    Tour save(@Param("tour") Tour tour);
    public int update(@Param("tour") Tour tour);
    void delete(@Param("tour") Tour tour);

}
