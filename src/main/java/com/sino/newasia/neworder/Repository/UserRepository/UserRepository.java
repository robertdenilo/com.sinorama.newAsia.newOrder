package com.sino.newasia.neworder.Repository.UserRepository;

import com.sino.newasia.neworder.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
