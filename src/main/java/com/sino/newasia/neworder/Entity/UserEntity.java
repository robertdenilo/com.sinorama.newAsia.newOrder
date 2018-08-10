package com.sino.newasia.neworder.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Officer")
public class UserEntity {



    @Id
    private String Id;
    private String UserName;
    private String Age;

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

}
