package com.sunvalley.user.model;

import java.util.Date;

public class User {
    private Integer id;

    private String userName;

    private String password;

    private String email;

    private Integer status;

    private String otheraccount;

    private Integer otheraccounttype;

    private String createby;

    private Date createdate;

    private String updateby;

    private Date updatedate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOtheraccount() {
        return otheraccount;
    }

    public void setOtheraccount(String otheraccount) {
        this.otheraccount = otheraccount == null ? null : otheraccount.trim();
    }

    public Integer getOtheraccounttype() {
        return otheraccounttype;
    }

    public void setOtheraccounttype(Integer otheraccounttype) {
        this.otheraccounttype = otheraccounttype;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
}