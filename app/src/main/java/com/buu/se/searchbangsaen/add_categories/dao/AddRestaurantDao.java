package com.buu.se.searchbangsaen.add_categories.dao;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Dell on 10/04/2560.
 */

public class AddRestaurantDao {
    private String nameRestaurant;
    private String resPhone;
    private String resOpen;
    private String resClose;
    private DateDao resDateDao;
    private TypeResDao resTypeDao;
    private BenefitDao resBenefitDao;
    private String resDate;
    private String resAddress;
    private LatLng resLatLng;

    public DateDao getResDateDao() {
        return resDateDao;
    }

    public void setResDateDao(DateDao resDateDao) {
        this.resDateDao = resDateDao;
    }

    public TypeResDao getResTypeDao() {
        return resTypeDao;
    }

    public void setResTypeDao(TypeResDao resTypeDao) {
        this.resTypeDao = resTypeDao;
    }

    public BenefitDao getResBenefitDao() {
        return resBenefitDao;
    }

    public void setResBenefitDao(BenefitDao resBenefitDao) {
        this.resBenefitDao = resBenefitDao;
    }

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }

    public String getResPhone() {
        return resPhone;
    }

    public void setResPhone(String resPhone) {
        this.resPhone = resPhone;
    }

    public String getResOpen() {
        return resOpen;
    }

    public void setResOpen(String resOpen) {
        this.resOpen = resOpen;
    }

    public String getResClose() {
        return resClose;
    }

    public void setResClose(String resClose) {
        this.resClose = resClose;
    }

    public String getResDate() {
        return resDate;
    }

    public void setResDate(String resDate) {
        this.resDate = resDate;
    }

    public String getResAddress() {
        return resAddress;
    }

    public void setResAddress(String resAddress) {
        this.resAddress = resAddress;
    }

    public LatLng getResLatLng() {
        return resLatLng;
    }

    public void setResLatLng(LatLng resLatLng) {
        this.resLatLng = resLatLng;
    }
}
