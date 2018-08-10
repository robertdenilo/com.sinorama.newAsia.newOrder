package com.sino.newasia.neworder.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@EnableAutoConfiguration
public class HelloIJ {

    @RequestMapping("/hello")
    private String index(){
       return "hello xxxx";
    }
}
