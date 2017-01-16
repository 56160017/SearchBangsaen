package com.buu.se.searchbangsaen.searchcategories;

import android.content.Context;

/**
 * Created by Dell on 10/01/2560.
 */
public class RestaurantDao {

    private int id;
    private String name;
    private String day;
    private String open;
    private String close;
    private String contact;
    private String status;
    private String distance;

    public RestaurantDao(int i, String name, String day, String open, String close
            , String contact, String status, String distance) {
        this.id = i;
        this.name = name;
        this.day = day;
        this.open = open;
        this.close = close;
        this.contact = contact;
        this.status = status;
        this.distance = distance;
    }

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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
