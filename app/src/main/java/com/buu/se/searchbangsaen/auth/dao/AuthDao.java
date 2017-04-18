package com.buu.se.searchbangsaen.auth.dao;

import android.net.Uri;

import java.net.URI;

/**
 * Created by Dell on 26/03/2560.
 */

public class AuthDao {
    private String uid;
    private String pwd;
    private String name;
    private String sname;
    private String numberphone;
    private String email;
    private int CategoriesID;
    private Uri uri;

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

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

    public int getCategoriesID() {
        return CategoriesID;
    }

    public void setCategoriesID(int categoriesID) {
        CategoriesID = categoriesID;
    }
}
