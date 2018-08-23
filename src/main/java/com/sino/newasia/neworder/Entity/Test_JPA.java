package com.sino.newasia.neworder.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "test3")
//@NamedNativeQuery(
@NamedStoredProcedureQuery(name = "proc1", procedureName = "proc1",
        resultClasses = { Test_JPA.class },
        parameters = { @StoredProcedureParameter(mode = ParameterMode.IN, name = "_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "_big_name ", type = String.class)})
@Getter
@Setter
public class Test_JPA {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", nullable = true, length = 20)
    private String name;
    @Column(name = "age", nullable = true, length = 4)
    private int age;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
