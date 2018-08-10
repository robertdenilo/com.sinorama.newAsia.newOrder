package com.sino.newasia.neworder.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@EnableAutoConfiguration
public class HelloIJ {

    @RequestMapping("/hello/{name}")
    private String index(@PathVariable("name") String Name){

        return String.format("hello xxxx  %s", Name);
    }

}
