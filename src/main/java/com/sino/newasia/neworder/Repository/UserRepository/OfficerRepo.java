package com.sino.newasia.neworder.Repository.UserRepository;

import com.sino.newasia.neworder.Entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfficerRepo extends JpaRepository<Officer, Integer> {

    @Query(value = "select new Officer(o.email,o.firstname,o.lastname,o.office,o.pwd,o.seeAll) from Officer  o where o.status = 1 and o.email = :email")
    public List<Officer> findAllByEmail(@Param("email") String email);

}
