package com.buu.se.searchbangsaen.auth.dao;

/**
 * Created by Dell on 26/03/2560.
 */

public class AuthDao {
    String uid;
    String pwd;
    String name;
    String sname;
    String numberphone;
    String email;
    String CategoriesID;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategoriesID() {
        return CategoriesID;
    }

    public void setCategoriesID(String categoriesID) {
        CategoriesID = categoriesID;
    }
}
