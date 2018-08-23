package com.sino.newasia.neworder.Repository.TestJPARepository;

import com.sino.newasia.neworder.Entity.Test_JPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface JPARepository extends JpaRepository<Test_JPA, Long> {

    @Query("select t from test3 t where t.name = ?1")
    List<Test_JPA> findByName(String name);


    @Query("select t from test3 t where t.name = ?1")
    List<Test_JPA> findAllByName(String name);


    List<Test_JPA> findFirst1ByName(String name);

    //only can get returned param
    @Procedure(procedureName = "proc1", outputParameterName = "_big_name")
    String callProc(String name);

    //get call return value
    List<Test_JPA> callStore(String name);

}
