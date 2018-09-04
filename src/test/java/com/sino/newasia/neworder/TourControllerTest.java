package com.sino.newasia.neworder;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
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
