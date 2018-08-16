package com.sino.newasia.neworder.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


//@NamedQuery(name="tour.maininfo",query="select t.tourid, t.routeid,t.departdate, t.tourpid, t.status from t_tour t")
@Entity
@Table(name = "test2")
public class Test2 implements Serializable {

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }


    @Id
    @Column(name="name1")
    private String name1;
    @Column(name="name2")
    private String name2;




}