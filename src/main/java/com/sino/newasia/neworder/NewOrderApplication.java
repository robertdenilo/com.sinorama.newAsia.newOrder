package com.sino.newasia.neworder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Properties;

@ServletComponentScan
@SpringBootApplication
@EnableJpaRepositories({"com.sino.newasia.neworder.Repository.TourRepository","com.sino.newasia.neworder.Repository.Test2Repository","com.sino.newasia.neworder.Repository.TestJPARepository"})
//@PropertySource("classpath:xxx.properties")
//@EnableAutoConfiguration
public class NewOrderApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewOrderApplication.class.getName());

    public static void main(String[] args) {
        //SpringApplication.run(NewOrderApplication.class, args);
        System.out.println(System.getProperty("java.class.path"));
        Properties prop = new Properties();
//        InputStream in = Application.class.getClassLoader().getResourceAsStream("classpath:application2.properties");
        try {
        File file = ResourceUtils.getFile("classpath:application2.properties");
        InputStream in = new FileInputStream(file);
        prop.load(in);

        } catch (FileNotFoundException e) {

        }catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        SpringApplication context = new SpringApplication(NewOrderApplication.class);
        context.setDefaultProperties(prop);
        context.run(args);  // context.getBean(Runnable.class).run();


    }


}
