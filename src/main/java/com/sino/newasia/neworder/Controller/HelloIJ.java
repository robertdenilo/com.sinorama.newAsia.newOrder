package com.sino.newasia.neworder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


@RestController
@RequestMapping("/test")
//@EnableAutoConfiguration
public class HelloIJ {

    @RequestMapping("/hello/{name}")
    private String index(@PathVariable("name") String Name){

        return String.format("hello xxxx  %s", Name);
    }
   @RequestMapping("/login")
    private String Login(@RequestParam String aa){
        return  "";
   }

   @Autowired
   private JdbcTemplate jdbcTemplate;
   @RequestMapping("/mydb/getData")         //use jdbcTemplate
   private List<Map<String, Object>>  DBTest(){
       String sql = "select * from t_user";
       List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
       for (Map<String, Object> map : list) {
           Set<Entry<String, Object>> entries = map.entrySet( );
           if(entries != null) {
               Iterator<Entry<String, Object>> iterator = entries.iterator( );
               while(iterator.hasNext( )) {
                   Entry<String, Object> entry =(Entry<String, Object>) iterator.next( );
                   Object key = entry.getKey( );
                   Object value = entry.getValue();
                   System.out.println(key+":"+value);
               }
           }
       }
       return list;
   }

    @RequestMapping("/mydb/getData/{id}")
    public Map<String,Object> DBTest_userid(@PathVariable Integer id){
        Map<String,Object> map = null;
        List<Map<String, Object>> list = DBTest();
        for (Map<String, Object> dbmap : list) {
            Set<String> set = dbmap.keySet();
            for (String key : set) {
                if(key.equals("id")){
                    if(dbmap.get(key).equals(id)){
                        map = dbmap;
                    }
                }
            }
        }
        if(map==null)
            map = list.get(0);
        return map;
    }
}
