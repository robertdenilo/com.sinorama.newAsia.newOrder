package com.sino.newasia.neworder.Controller;

import com.sino.newasia.neworder.Entity.Test;
import com.sino.newasia.neworder.Entity.Test2;
import com.sino.newasia.neworder.Entity.Tour;
import com.sino.newasia.neworder.Entity.UserEntity;
import com.sino.newasia.neworder.Repository.TourRepository.TestRepository;
import com.sino.newasia.neworder.Repository.TourRepository.TourRepository;
import com.sino.newasia.neworder.Repository.UserRepository.UserRepository;
import com.sino.newasia.neworder.Service.Test2Service.Test2ServiceInt;
import com.sino.newasia.neworder.Service.TourService.TourServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

;

@RestController
@RequestMapping("/test")
//@EnableAutoConfiguration
public class HelloIJ {

//    @RequestMapping("/hello/{name}")
//    private String index(@PathVariable("name") String Name){
//
//        return String.format("hello xxxx  %s", Name);
//    }
//   @RequestMapping("/login")
//    private String Login(@RequestParam String aa){
//        return  "";
//   }
//



//   //Test JdbcTemplate func with app.prop setting
//   @Autowired
//   private JdbcTemplate jdbcTemplate;
//   @RequestMapping("/mydb/getData")         //use jdbcTemplate
//   private List<Map<String, Object>> DBTest(){
//       String sql = "select t.email,t.firstname,t.lastname,t.modifytime from t_officer t";
//       List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
//       for (Map<String, Object> map : list) {
//           Set<Entry<String, Object>> entries = map.entrySet( );
//           if(entries != null) {
//               Iterator<Entry<String, Object>> iterator = entries.iterator( );
//               while(iterator.hasNext( )) {
//                   Entry<String, Object> entry =(Entry<String, Object>) iterator.next( );
//                   Object key = entry.getKey( );
//                   Object value = entry.getValue();
//                   System.out.println(key+":"+value);
//               }
//           }
//       }
//       return list;
//   }

//    @RequestMapping("/mydb/getData/{id}")
//    public Map<String,Object> DBTest_userid(@PathVariable String id){
//        Map<String,Object> map = null;
//        List<Map<String, Object>> list = DBTest();
//        for (Map<String, Object> dbmap : list) {
//            Set<String> set = dbmap.keySet();
//            for (String key : set) {
//                if(key.equals("email")){
//                    if(dbmap.get(key).equals(id)){
//                        map = dbmap;
//                    }
//                }
//            }
//        }
//        if(map==null)
//            map = list.get(0);
//        return map;
//    }
//
//
//      //Test Redis func, with RedisService, app.prop setting
////    @Resource(name = "redisTemplate")
////    private ListOperations<String, String> messageList;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/myredis/try")
    public void Redis_Test(){
        UserEntity user = new UserEntity();
        user.setAge("newdata");
        user.setId("springboot");
        user.setUserName("Zhouming");
        userRepository.save(user);

    }



    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TestRepository testRepository;
    //use JPA to connect DB with JPA config in app.prop, Test Entity, TestRepository interface (jump service layer) and notice: @EnableJpaRepositories("com.sino.newasia.neworder.Repository")
    @GetMapping(value="/getTest")
    public List<Test> getTest(){
        return testRepository.findAll();
    }

//    @GetMapping(value="/getTour")
//    public List<Tour> getTour(){
//        return tourRepository.findAll();
//    }

    @Autowired
    public TourServiceInt tourServiceInt;
    @GetMapping(value="/getTourRedis")
    public List<Tour> getTourWithRedisCache(){
        System.out.println("只有第一次才会打印sql语句");
        return tourServiceInt.getTourWithRedisCache();
    }

    @Autowired
    public Test2ServiceInt test2ServiceInt;
    @GetMapping(value="/getTest2Redis")
    public List<Test2> getTest2WithRedisCache(){
        System.out.println("Test2:只有第一次才会打印sql语句");
        return test2ServiceInt.getTest2WithRedisCache();
    }

}
