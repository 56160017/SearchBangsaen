package com.buu.se.searchbangsaen.searchcategories.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Dell on 10/01/2560.
 */
public class RestaurantDao implements Parcelable {

    private int id;
    private String name;
    private String day;
    private String open;
    private String close;
    private String contact;
    private String status;
    private String distance;
    private String location;
    private BenefitsDao BenefitsDao;
    private List<String> tfDao;

    public RestaurantDao(int i, String name, String day, String open, String close
            , String contact, String location, String status, String distance,List<String> tfDao,BenefitsDao BenefitsDao) {
        this.id = i;
        this.name = name;
        this.day = day;
        this.open = open;
        this.close = close;
        this.contact = contact;
        this.location = location;
        this.status = status;
        this.distance = distance;
        this.tfDao = tfDao;
        this.BenefitsDao = BenefitsDao;

    }


    protected RestaurantDao(Parcel in) {
        id = in.readInt();
        name = in.readString();
        day = in.readString();
        open = in.readString();
        close = in.readString();
        contact = in.readString();
        status = in.readString();
        distance = in.readString();
        location = in.readString();
        BenefitsDao = in.readParcelable(com.buu.se.searchbangsaen.searchcategories.dao.BenefitsDao.class.getClassLoader());
        tfDao = in.createStringArrayList();
    }

    public static final Creator<RestaurantDao> CREATOR = new Creator<RestaurantDao>() {
        @Override
        public RestaurantDao createFromParcel(Parcel in) {
            return new RestaurantDao(in);
        }

        @Override
        public RestaurantDao[] newArray(int size) {
            return new RestaurantDao[size];
        }
    };

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BenefitsDao getBenefitsDao() {
        return BenefitsDao;
    }
    public void setBenefitsDao(BenefitsDao benefitsDao) {
        BenefitsDao = benefitsDao;
    }

    public List<String> getTfDao() {
        return tfDao;
    }

    public void setTfDao( List<String> tfDao) {
        this.tfDao = tfDao;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(day);
        dest.writeString(open);
        dest.writeString(close);
        dest.writeString(contact);
        dest.writeString(status);
        dest.writeString(distance);
        dest.writeString(location);
        dest.writeParcelable(BenefitsDao, flags);
        dest.writeStringList(tfDao);
    }
}