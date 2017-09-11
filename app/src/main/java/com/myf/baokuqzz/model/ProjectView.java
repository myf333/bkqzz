package com.myf.baokuqzz.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by myf on 2017/8/22.
 */

public class ProjectView{
    /**
     * 主键
     */
    private int id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目编号
     */
    private String code;
    /**
     * 项目类型
     */
    private String projecttype;
    /**
     * 城市
     */
    private String city;
    /**
     * 地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 服务内容
     */
    private String servicecontent;
    /**
     * 项目简介
     */
    private String memo;
    /**
     * 经度
     */
    private double X;
    /**
     * 纬度
     */
    private double Y;
    /**
     * 上线时间
     */
    private String publishdate;
    /**
     * 距离,单位公里
     */
    private double distance;

    private String projectstatus;
    /**
     * 现场图片列表
     */
    private List<ProjectPicView> piclist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getServicecontent() {
        return servicecontent;
    }

    public void setServicecontent(String servicecontent) {
        this.servicecontent = servicecontent;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getProjectstatus() {
        return projectstatus;
    }

    public void setProjectstatus(String projectstatus) {
        this.projectstatus = projectstatus;
    }

    public List<ProjectPicView> getPiclist() {
        return piclist;
    }

    public void setPiclist(List<ProjectPicView> piclist) {
        this.piclist = piclist;
    }
}
