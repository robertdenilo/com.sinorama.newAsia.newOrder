package com.sino.newasia.neworder.Controller;


import com.alibaba.fastjson.JSON;
import com.sino.newasia.neworder.Entity.Tour;
import com.sino.newasia.neworder.Repository.TourRepository.TourRepository;
import com.sino.newasia.neworder.Service.TourService.TourServiceCached;
import com.sino.newasia.neworder.Service.TourService.TourServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.UUID;

import static com.sino.newasia.neworder.Pojo.TourSpecs.getTourSpec;

@RestController
@RequestMapping(value = "asiaTour")
public class TourController {
    @Autowired
    private TourRepository tourRepository;
    //http://localhost:9999/jpatest/getRec?name=lll
    @GET
    @Produces("application/json")   //application/xml
    @RequestMapping("/getTours/{tourid}")
    public Object getToursByTourid(@PathVariable("tourid") String tourid) {
        System.out.println("jsonjson: "+ tourid);
        return tourRepository.findAllByTourid(tourid);
    }


    @GET
    @Produces("application/json")   //application/xml
    @RequestMapping("/getToursByRouteId/{routeid}")
    public Object getToursByRouteid(@PathVariable("routeid") String routeid) {
        System.out.println("jsonjson: "+ routeid);
        return tourRepository.findAllByRouteid(routeid);
    }


    //http://localhost:9999/asiaTour/getToursFromPojo/BHHS-11-2019
    @GET
    @Produces("application/json")   //application/xml
    @RequestMapping("/getToursFromPojo/{routeid}")
    public Object getToursFromPojo(@PathVariable("routeid") String routeid) {
        System.out.println("jsonjson pojo: "+ routeid);
        return tourRepository.findAll(getTourSpec());
    }


    @GET
    @Produces("application/json")   //application/xml
    @RequestMapping("/getToursFromEM/{routeid}")
    public Object getToursFromEM(@PathVariable("routeid") String routeid) {
        System.out.println("jsonjson pojo: "+ routeid);
        List<Object[]> ob = tourRepository.doTest();
        System.out.println(ob.toArray());
        return ob;
    }

    @PersistenceUnit
    private EntityManagerFactory emf;


    //http://localhost:9999/asiaTour/getToursFromNativeEm/BHHS-11-2019
    //use non-native method，such as createQuery of em, table name must be class name of table mapping.
    @GET
    @Produces("application/json")   //application/xml
    @RequestMapping("/getToursFromNativeEm/{routeid}")
    public List  getToursFromNativeEm(@PathVariable("routeid") String routeid, Model model){ //return List=>String
        System.out.println("jsonjson em: "+ routeid);
        EntityManager em = emf.createEntityManager(); // With parameter
        List arr_cust = em
                .createQuery("SELECT t FROM Tour t WHERE t.routeid = :routeid ")
                .setParameter("routeid",routeid)
                .getResultList();
        return arr_cust;
        //model.addAttribute("tours",arr_cust);
        //return "tourResult";       //if use thymeleaf: 1 must be Controller, can NOT be RestController, 2 add Model model in parameters list. 3, return String
    }


    //http://localhost:9999/asiaTour/getToursPage/BHHS-11-2019
    @GET
    @Produces("application/json")   //application/xml
    @RequestMapping("/getToursPage/{routeid}")
    public Object getToursPage(@PathVariable("routeid") String routeid) {
        System.out.println("jsonjson pojo: "+ routeid);
        return tourRepository.findAll(new PageRequest(2,3, Sort.Direction.ASC, "departdate"));
    }



    @Autowired
    public TourServiceInt tourServiceInt;
    @GetMapping(value="/getToursPage2/{routeid}")
    public Page<Tour> getTourWithPaged(){
        System.out.println("只有第一次才会打印sql语句");
        return tourServiceInt.findTourNoCriteria(2,3);
    }

    @Autowired
    public Test ttt;

    @GetMapping(value="/getToursPageWithCriteria/{routeid}/{departdate}")
    public List<Tour> getTourWithCriteria(@PathVariable("routeid") String routeid, @PathVariable("departdate") String departdate){
        System.out.println("page with criteria");
        Tour t1 = new Tour();

        t1.setRouteid(routeid);
        t1.setDepartdate(departdate);
        Pageable pageable = new PageRequest(3, 10, Sort.Direction.ASC, "departdate");
        Page<Tour> pt = tourServiceInt.findTourCriteria(t1, pageable);
        List<Tour> lt = pt.getContent();

        //while(!pt.isLast()){
            pt = tourServiceInt.findTourCriteria( t1, pageable.next());
            List<Tour> listData = pt.getContent();

        //}
        lt.addAll(listData);
        System.out.println( JSON.toJSONString(listData));
        // lt.addAll(listData);
        System.out.println((int)ttt.getBytes("nihao".toCharArray())[0]);

        return lt;
    }



    //test TourServiceCached
    @Autowired
    private TourServiceCached tourServiceCached;
    @RequestMapping(value = "/redis", method = RequestMethod.GET)
    public String redisTransactionTest(){
        System.out.println("====== 进行 Redis 缓存试验 ======");
        Tour tour = new Tour();
        //create tourid
        String u1_uuid = UUID.randomUUID().toString();
        String uuid1 = u1_uuid.substring(0,8)+u1_uuid.substring(9,13)+u1_uuid.substring(14,18)+u1_uuid.substring(19,23)+u1_uuid.substring(24);
        String toudid = "20180910-IT-TEST" + uuid1;
        tour.setStatus("4");
        tour.setRouteid("IT-ROUTE1");
        tour.setDepartdate("2018-09-10");
        tour.setTourid(toudid);
        tour.setTourpid(toudid);

        try {
            tourServiceCached.save(tour);
        } catch (Exception e) {
            System.out.println("保存用户出现异常");
        }

        //第一次查询 //com.sino.newasia.neworder.Entity.Tour@7253eb13
        System.out.println(tourServiceCached.findByTourid(tour.getTourid()));
        //通过缓存查询  //没有走缓存！ only display once. //com.sino.newasia.neworder.Entity.Tour@1f52686c
        //problem:Java.lang.ClassCastException: cannot be cast to same object, caused by
        //cause:Tour loaded by the app class loader being cast to the Tour type loaded by the restart class loader.
        //doc:https://docs.spring.io/spring-boot/docs/1.5.3.RELEASE/reference/htmlsingle/#using-boot-devtools-customizing-classload:20.2.5
        //solution:if not use spring-boot-devtools, it will be OK.
        System.out.println(tourServiceCached.findByTourid(tour.getTourid()));

        System.out.println("====== 修改 Redis 缓存数据 ======");
        //修改用户数据
        tour.setStatus("5");
        tour.setRouteid("IT-ROUTE2");
        tour.setDepartdate("2018-09-11");
        System.out.println(tourServiceCached.update(tour));

        System.out.println(tourServiceCached.findByTourid(tour.getTourid()));

        tourServiceCached.delete(toudid);
        System.out.println("====== delete in Redis:  ======"+toudid);
        return "success";
    }

}
