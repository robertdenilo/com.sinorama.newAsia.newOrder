package com.sino.newasia.neworder.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_officer")
@Getter
@Setter
public class Officer {
    @Id
    @Column(name = "email", nullable = false, length = 20)
    private String email;
    @Column(name = "firstname", nullable = true, length = 50)
    private String firstname;
    @Column(name = "lastname", nullable = true, length = 50)
    private String lastname;
    @Column(name = "office", nullable = true, length = 20)
    private String office;
    @Column(name = "seeAll", nullable = true, length = 2)
    private String seeAll;
    @Column(name = "pwd", nullable = true, length = 128)
    private String pwd;
    @Column(name = "status", nullable = false, length = 1)
    private String status;

    public Officer(String email,String firstname,String lastname,String office,String pwd,String seeAll){
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.office = office;
        this.pwd = pwd;
        this.seeAll = seeAll;

    }

}
