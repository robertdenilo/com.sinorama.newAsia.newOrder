package com.sino.newasia.neworder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.sino.newasia.neworder.Repository.TourRepository","com.sino.newasia.neworder.Repository.Test2Repository"})
public class NewOrderApplication {

    public static void main(String[] args) {

        SpringApplication.run(NewOrderApplication.class, args);
    }


}
