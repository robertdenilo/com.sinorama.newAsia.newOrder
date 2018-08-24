package com.sino.newasia.neworder.Service.TourService;

import com.sino.newasia.neworder.Entity.Tour;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

public class TourServiceTest {

    private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:src/application.xml");
    private static TourService tourService = (TourService) context.getBean("tourService");
    public void saveUser() {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        sw.start("Add a new tour information.");
        Tour t = new Tour();
        t.setTourid("E9999-2020-IT-TEST");
        t.setRouteid("ABC");
        t.setDepartdate("2020-01-01");
        t.setStatus("9");
        t.setTourpid("E9999-2020-IT-TEST");
        tourService.save(t);
        sw.stop();
        System.err.println(sw.prettyPrint());
    }

    public static void main(String[] args) {
        TourServiceTest test = new TourServiceTest();
        test.saveUser();
    }

}
