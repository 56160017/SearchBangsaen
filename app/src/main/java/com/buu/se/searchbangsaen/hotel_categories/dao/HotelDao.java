package com.buu.se.searchbangsaen.hotel_categories.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dell on 10/03/2560.
 */
public class HotelDao implements Parcelable {

    private int id;
    private String name;
    private int empty_room;
    private String contact;
    private int price_f;
    private int price_t;
    private String distance;

    public HotelDao(int id, String name, int empty_room, String contact, int price_f, int price_t, String distance) {
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
        empty_room = in.readInt();
        contact = in.readString();
        price_f = in.readInt();
        price_t = in.readInt();
        distance = in.readString();
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

    public int getEmpty_room() {
        return empty_room;
    }

    public void setEmpty_room(int empty_room) {
        this.empty_room = empty_room;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getPrice_f() {
        return price_f;
    }

    public void setPrice_f(int price_f) {
        this.price_f = price_f;
    }

    public int getPrice_t() {
        return price_t;
    }

    public void setPrice_t(int price_t) {
        this.price_t = price_t;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(empty_room);
        dest.writeString(contact);
        dest.writeInt(price_f);
        dest.writeInt(price_t);
        dest.writeString(distance);
    }
}
