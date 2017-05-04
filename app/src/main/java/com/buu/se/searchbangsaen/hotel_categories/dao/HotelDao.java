package com.buu.se.searchbangsaen.hotel_categories.dao;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Dell on 10/03/2560.
 */
public class HotelDao implements Parcelable {

    private int id;
    private String name;
    private String empty_room;
    private String phone;
    private String contact;
    private String price_f;
    private String price_t;
    private String distance;
    private double latitude;
    private double longitude;
    private BenefitsHotelDao benefitHotelDao;
    private RelaxsDao relaxDao;
    private List<Uri> mUri;



    public HotelDao() {

    }

    public HotelDao(int id, String name, String empty_room, String contact, String price_f, String price_t, String distance) {
        this.id = id;
        this.name = name;
        this.empty_room = empty_room;
        this.contact = contact;
        this.price_t = price_t;
        this.price_f = price_f;
        this.distance = distance;
    }


    protected HotelDao(Parcel in) {
        id = in.readInt();
        name = in.readString();
        empty_room = in.readString();
        phone = in.readString();
        contact = in.readString();
        price_f = in.readString();
        price_t = in.readString();
        distance = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        benefitHotelDao = in.readParcelable(com.buu.se.searchbangsaen.hotel_categories.dao.BenefitsHotelDao.class.getClassLoader());
        relaxDao = in.readParcelable(com.buu.se.searchbangsaen.hotel_categories.dao.RelaxsDao.class.getClassLoader());
        mUri = in.createTypedArrayList(Uri.CREATOR);
    }

    public static final Creator<HotelDao> CREATOR = new Creator<HotelDao>() {
        @Override
        public HotelDao createFromParcel(Parcel in) {
            return new HotelDao(in);
        }

        @Override
        public HotelDao[] newArray(int size) {
            return new HotelDao[size];
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmpty_room() {
        return empty_room;
    }

    public void setEmpty_room(String empty_room) {
        this.empty_room = empty_room;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPrice_f() {
        return price_f;
    }

    public void setPrice_f(String price_f) {
        this.price_f = price_f;
    }

    public String getPrice_t() {
        return price_t;
    }

    public void setPrice_t(String price_t) {
        this.price_t = price_t;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public BenefitsHotelDao getBenefitHotelDao() {
        return benefitHotelDao;
    }

    public void setBenefitHotelDao(BenefitsHotelDao benefitHotelDao) {
        this.benefitHotelDao = benefitHotelDao;
    }

    public List<Uri> getmUri() {
        return mUri;
    }

    public void setmUri(List<Uri> mUri) {
        this.mUri = mUri;
    }

    public RelaxsDao getRelaxDao() {
        return relaxDao;
    }

    public void setRelaxDao(RelaxsDao relaxDao) {
        this.relaxDao = relaxDao;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(empty_room);
        dest.writeString(phone);
        dest.writeString(contact);
        dest.writeString(price_f);
        dest.writeString(price_t);
        dest.writeString(distance);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeParcelable(benefitHotelDao, flags);
        dest.writeParcelable(relaxDao, flags);
        dest.writeTypedList(mUri);

    }
}
