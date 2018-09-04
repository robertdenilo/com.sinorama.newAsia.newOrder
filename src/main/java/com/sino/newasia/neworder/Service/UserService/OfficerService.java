package com.sino.newasia.neworder.Service.UserService;

import com.sino.newasia.neworder.Entity.Officer;
import com.sino.newasia.neworder.Repository.UserRepository.OfficerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficerService implements OfficerServiceInt {
    @Autowired
    private OfficerRepo officeRepo;

    public Officer getOfficerByName(String email){
        return officeRepo.findAllByEmail(email).get(0);
    }
    public void save(Officer officer){
        officeRepo.save(officer);
    }


    public boolean verify(String username, String pwd){
        Officer userEntity = officeRepo.findAllByEmail(username).get(0);
        String encryptPwd = userEntity.getPwd();
        return true;
    }
}
