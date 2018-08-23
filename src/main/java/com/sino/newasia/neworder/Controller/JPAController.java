package com.sino.newasia.neworder.Controller;


import com.sino.newasia.neworder.Entity.Test_JPA;
import com.sino.newasia.neworder.Repository.TestJPARepository.JPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping(value = "jpatest")
public class JPAController {

    @Autowired
    private JPARepository jpaRepository;

    //http://localhost:9999/jpatest/addPerson
    // add consumes = MediaType.APPLICATION_JSON_VALUE can handle: pass in jason
    // {
    //"name":"zhouming",
    //"age":123
    //}
    // default handle :pass in x-www-form-urlencoded and form-data:    MediaType.APPLICATION_FORM_URLENCODED_VALUE

    @PostMapping(path = "/addPerson", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addRec(@RequestBody Test_JPA TJpa) {

        System.out.println("pass in: "+TJpa.getName() + " and "+TJpa.getAge());

        jpaRepository.save(TJpa);
    }
    @PostMapping(path = "/{userId}/addPerson3")
    public void addRec(@PathVariable String userId, @RequestBody Test_JPA TJpa) {

        System.out.println("pass in: "+TJpa.getName() + " and "+TJpa.getAge() + " and" + userId);

        jpaRepository.save(TJpa);
    }
    //http://localhost:9999/jpatest/good_persons/addPerson2
    @GetMapping(path = "/{userId}/addPerson2")
    public void addRec2(@PathVariable String userId) {
        System.out.println("pass in:  userid: "+userId);
    }


    @DeleteMapping(path = "deletePerson")
    public void deleteRec(Long id) {
        jpaRepository.deleteById(id);
    }


    //http://localhost:9999/jpatest/getRec?name=lll
    @GET
    @Produces("application/json")   //application/xml
    @RequestMapping("/getRec")
    public Object getRec(@QueryParam("name") String name) {
        System.out.println("jsonjson:"+name);
        return jpaRepository.findAll();
    }

    //http://localhost:9999/jpatest/getRec2?name=3333
    @GET
    @Produces("application/json")   //application/xml
    @RequestMapping("/getRec2")
    public Object getRec2(@QueryParam("name") String name) {
        System.out.println("jsonjson2:"+name);
       // return jpaRepository.findFirst1ByName(name);
        return jpaRepository.findAllByName(name);
    }

    @GET
    @Produces("application/json")   //application/xml
    @RequestMapping("/getRec3/{name}")
    public List<Test_JPA> getRec3(@PathVariable("name") String name) {
        System.out.println("jsonjson3:"+name);
        return jpaRepository.callStore(name);
    }


    @GET
    @Produces("application/json")   //application/xml
    @RequestMapping("/getRec4/{name}")
    public String getRec4(@PathVariable("name") String name) {
        System.out.println("jsonjson4:"+name);
        return jpaRepository.callProc(name);

    }
}
