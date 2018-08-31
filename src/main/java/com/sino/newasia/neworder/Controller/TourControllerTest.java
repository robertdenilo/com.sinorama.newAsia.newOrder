package com.sino.newasia.neworder.Controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TourControllerTest {
    @Test
    public void test(){
        Map<String,Object> map = new HashMap();
        map.put("start",0);
        map.put("size",8);

        System.out.println("size:"+map.size());
        System.out.println("-----测试完毕-------");

    }
}
