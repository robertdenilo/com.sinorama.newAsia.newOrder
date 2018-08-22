package com.sino.newasia.neworder.Repository.TestJPARepository;

import com.sino.newasia.neworder.Entity.Test_JPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JPARepository extends JpaRepository<Test_JPA, Long> {

    @Query("select t from test3 t where t.name = ?1")
    List<Test_JPA> findByName(String name);


    @Query("select t from test3 t where t.name = ?1")
    List<Test_JPA> findAllByName(String name);


    List<Test_JPA> findFirst1ByName(String name);
}
