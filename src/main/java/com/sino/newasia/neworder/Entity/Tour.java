package com.sino.newasia.neworder.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//@NamedQuery(name="tour.maininfo",query="select t.tourid, t.routeid,t.departdate, t.tourpid, t.status from t_tour t")
@Entity
@Table(name = "t_tour")
public class Tour {
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



    public String getDepartdate() {
        return departdate;
    }

    public void setDepartdate(String departdate) {
        this.departdate = departdate;
    }

    public String getTourpid() {
        return tourpid;
    }

    public void setTourpid(String tourpid) {
        this.tourpid = tourpid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Id
    @Column(name="tourid")
    private String tourid;
    @Column(name="routeid")
    private String routeid;
    @Column(name="departdate")
    private String departdate;
    @Column(name="tourpid")
    private String tourpid;
    @Column(name="status")
    private String status;

    public Tour(){

    }



}
