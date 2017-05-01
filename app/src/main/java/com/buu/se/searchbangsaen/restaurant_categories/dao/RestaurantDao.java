package com.buu.se.searchbangsaen.restaurant_categories.dao;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.buu.se.searchbangsaen.add_categories.dao.TypeResDao;

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
    private boolean status;
    private String distance;
    private String location;
    private double latitude;
    private double longitude;
    private DatesDao datesDao;
    private BenefitsDao BenefitsDao;
    private TypeResDao typeResDao;
    private List<Uri> mUri;



    public RestaurantDao(){

    }

    public RestaurantDao(int i, String name, String day, String open, String close,
                         String contact, String location, boolean status, String distance,
                         double latitude, double longitude,TypeResDao typeResDao, BenefitsDao BenefitsDao) {
        this.id = i;
        this.name = name;
        this.day = day;
        this.open = open;
        this.close = close;
        this.contact = contact;
        this.location = location;
        this.status = status;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.typeResDao = typeResDao;
        this.BenefitsDao = BenefitsDao;

    }


    protected RestaurantDao(Parcel in) {
        id = in.readInt();
        name = in.readString();
        day = in.readString();
        open = in.readString();
        close = in.readString();
        contact = in.readString();
        status = in.readByte() != 0;
        distance = in.readString();
        location = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        datesDao = in.readParcelable(DatesDao.class.getClassLoader());
        BenefitsDao = in.readParcelable(com.buu.se.searchbangsaen.restaurant_categories.dao.BenefitsDao.class.getClassLoader());
        typeResDao = in.readParcelable(TypeResDao.class.getClassLoader());
        mUri = in.createTypedArrayList(Uri.CREATOR);
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public DatesDao getDatesDao() {
        return datesDao;
    }

    public void setDatesDao(DatesDao datesDao) {
        this.datesDao = datesDao;
    }

    public BenefitsDao getBenefitsDao() {
        return BenefitsDao;
    }
    public void setBenefitsDao(BenefitsDao benefitsDao) {
        BenefitsDao = benefitsDao;
    }


    public TypeResDao getTypeResDao() {
        return typeResDao;
    }

    public void setTypeResDao(TypeResDao typeResDao) {
        this.typeResDao = typeResDao;
    }
    public List<Uri> getmUri() {
        return mUri;
    }

    public void setmUri(List<Uri> mUri) {
        this.mUri = mUri;
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
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeString(distance);
        dest.writeString(location);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeParcelable(datesDao, flags);
        dest.writeParcelable(BenefitsDao, flags);
        dest.writeParcelable(typeResDao, flags);
        dest.writeTypedList(mUri);
    }
}