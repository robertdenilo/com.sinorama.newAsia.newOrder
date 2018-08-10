package com.sino.newasia.neworder.UserRepository;

import com.sino.newasia.neworder.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
