package com.sino.newasia.neworder.Controller;


import com.sino.newasia.neworder.Repository.TourRepository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.util.List;

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
    public List  getToursFromNativeEm(@PathVariable("routeid") String routeid){
        System.out.println("jsonjson em: "+ routeid);
        EntityManager em = emf.createEntityManager(); // With parameter
        List arr_cust = em
                .createQuery("SELECT t FROM Tour t WHERE t.routeid = :routeid ")
                .setParameter("routeid",routeid)
                .getResultList();
        return arr_cust;
    }


    //http://localhost:9999/asiaTour/getToursPage/BHHS-11-2019
    @GET
    @Produces("application/json")   //application/xml
    @RequestMapping("/getToursPage/{routeid}")
    public Object getToursPage(@PathVariable("routeid") String routeid) {
        System.out.println("jsonjson pojo: "+ routeid);
        return tourRepository.findAll(new PageRequest(2,3));
    }

}
