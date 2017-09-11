package com.myf.baokuqzz.model;

/**
 * Created by myf on 2017/8/18.
 */

public class RegUserView {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证
     */
    private String idcard;
    /**
     * 支付会员id
     */
    private Long memberid;
    /**
     * 最后登录时间
     */
    private String lastlogindate;
    /**
     * 创建时间
     */
    private String createdate;
    /**
     * 头像
     */
    private String picurl;
    /**
     *是否实名认证
     */
    private Boolean isvalidname;
    /**
     * 邮箱
     * */
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(String lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public Boolean getIsvalidname() {
        return isvalidname;
    }

    public void setIsvalidname(Boolean isvalidname) {
        this.isvalidname = isvalidname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RegUserView{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", isvalidname=" + isvalidname +
                '}';
    }
}
