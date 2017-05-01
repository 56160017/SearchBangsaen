package com.buu.se.searchbangsaen.restaurant_categories.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dell on 30/04/2560.
 */

public class DatesDao implements Parcelable{
    private boolean Sun;
    private boolean Monday;
    private boolean tuesday;
    private boolean Wed;
    private boolean Thursday;
    private boolean Friday;
    private boolean Saturday;

    public DatesDao(Parcel in) {
        Sun = in.readByte() != 0;
        Monday = in.readByte() != 0;
        tuesday = in.readByte() != 0;
        Wed = in.readByte() != 0;
        Thursday = in.readByte() != 0;
        Friday = in.readByte() != 0;
        Saturday = in.readByte() != 0;
    }

    public static final Creator<DatesDao> CREATOR = new Creator<DatesDao>() {
        @Override
        public DatesDao createFromParcel(Parcel in) {
            return new DatesDao(in);
        }

        @Override
        public DatesDao[] newArray(int size) {
            return new DatesDao[size];
        }
    };

    public DatesDao() {

    }

    public boolean isSun() {
        return Sun;
    }

    public void setSun(boolean sun) {
        Sun = sun;
    }

    public boolean isMonday() {
        return Monday;
    }

    public void setMonday(boolean monday) {
        Monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWed() {
        return Wed;
    }

    public void setWed(boolean wed) {
        Wed = wed;
    }

    public boolean isThursday() {
        return Thursday;
    }

    public void setThursday(boolean thursday) {
        Thursday = thursday;
    }

    public boolean isFriday() {
        return Friday;
    }

    public void setFriday(boolean friday) {
        Friday = friday;
    }

    public boolean isSaturday() {
        return Saturday;
    }

    public void setSaturday(boolean saturday) {
        Saturday = saturday;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (Sun ? 1 : 0));
        dest.writeByte((byte) (Monday ? 1 : 0));
        dest.writeByte((byte) (tuesday ? 1 : 0));
        dest.writeByte((byte) (Wed ? 1 : 0));
        dest.writeByte((byte) (Thursday ? 1 : 0));
        dest.writeByte((byte) (Friday ? 1 : 0));
        dest.writeByte((byte) (Saturday ? 1 : 0));
    }
}
