package com.myf.baokuqzz.model;

/**
 * Created by myf on 2017/8/22.
 */

public class MyBox {
    private String projectname;
    private String boxtype;
    private String boxsize;
    private String inputdate;
    private int id;
    private int rentduration;
    private String durationunit;
    private String begindate;
    private String enddate;
    private double rentprice;
    private double deposit;
    private double managefee;

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getBoxtype() {
        return boxtype;
    }

    public void setBoxtype(String boxtype) {
        this.boxtype = boxtype;
    }

    public String getBoxsize() {
        return boxsize;
    }

    public void setBoxsize(String boxsize) {
        this.boxsize = boxsize;
    }

    public String getInputdate() {
        return inputdate;
    }

    public void setInputdate(String inputdate) {
        this.inputdate = inputdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRentduration() {
        return rentduration;
    }

    public void setRentduration(int rentduration) {
        this.rentduration = rentduration;
    }

    public String getDurationunit() {
        return durationunit;
    }

    public void setDurationunit(String durationunit) {
        this.durationunit = durationunit;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public double getRentprice() {
        return rentprice;
    }

    public void setRentprice(double rentprice) {
        this.rentprice = rentprice;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getManagefee() {
        return managefee;
    }

    public void setManagefee(double managefee) {
        this.managefee = managefee;
    }
}
