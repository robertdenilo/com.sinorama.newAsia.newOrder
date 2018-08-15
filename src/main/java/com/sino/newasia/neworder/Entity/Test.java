package com.sino.newasia.neworder.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//@NamedQuery(name="tour.maininfo",query="select t.tourid, t.routeid,t.departdate, t.tourpid, t.status from t_tour t")
@Entity
@Table(name = "test")
public class Test {
    public String getTourid() {
        return tourid;
    }

    public void setTourid(String tourid) {
        this.tourid = tourid;
    }

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    @Id
    @Column(name="name1")
    private String tourid;
    @Column(name="name2")
    private String routeid;




}