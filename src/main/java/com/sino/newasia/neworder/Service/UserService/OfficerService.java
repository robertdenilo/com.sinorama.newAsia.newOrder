package com.sino.newasia.neworder.Service.UserService;

import com.sino.newasia.neworder.Entity.Officer;
import com.sino.newasia.neworder.Repository.UserRepository.OfficerRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class OfficerService implements OfficerServiceInt {
    @Autowired
    private OfficerRepo officeRepo;

    @Resource
    private RedisTemplate<String, Officer> redisTemplate;

    public Officer getOfficerByName(String email){
        Officer officer =  officeRepo.findAllByEmail(email).get(0);
        return officer;
    }
    public void addUserInRedisSession(String info, HttpSession hs){
       if(!redisTemplate.opsForHash().hasKey(hs.getId(),"userInfo")){
           redisTemplate.opsForHash().put(hs.getId(),"userInfo", info);
       }
    }
    public void save(Officer officer){
        officeRepo.save(officer);
    }


    public boolean verify(String username, String pwd){
        Officer userEntity = officeRepo.findAllByEmail(username).get(0);
        String encryptPwd = userEntity.getPwd();
        String newEncryptPwd = convertPhpPwd(encryptPwd);
        if(BCrypt.checkpw(pwd,newEncryptPwd)){
            System.out.println("Matched");
            return true;
        }else{
            System.out.println("Not Matched");
            return false;
        }

    }
    public String convertPhpPwd(String pwd){
        return "$2a".concat(pwd.substring(3));
    }
}
