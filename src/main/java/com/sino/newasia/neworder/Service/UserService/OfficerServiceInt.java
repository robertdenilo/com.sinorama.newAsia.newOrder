package com.sino.newasia.neworder.Service.UserService;

import com.sino.newasia.neworder.Entity.Officer;

public interface OfficerServiceInt {

    public Officer getOfficerByName(String email);
    public void save(Officer officer);
}
