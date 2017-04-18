package com.buu.se.searchbangsaen.hotel_categories.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dell on 15/04/2560.
 */
public class BenefitsHotelDao implements Parcelable{
    private boolean chk_internat;
    private boolean chk_shop;
    private boolean chk_dry;
    private boolean chk_parking;
    private boolean chk_taxi;

    public BenefitsHotelDao(Parcel in) {
        chk_internat = in.readByte() != 0;
        chk_shop = in.readByte() != 0;
        chk_dry = in.readByte() != 0;
        chk_parking = in.readByte() != 0;
        chk_taxi = in.readByte() != 0;
    }

    public BenefitsHotelDao() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (chk_internat ? 1 : 0));
        dest.writeByte((byte) (chk_shop ? 1 : 0));
        dest.writeByte((byte) (chk_dry ? 1 : 0));
        dest.writeByte((byte) (chk_parking ? 1 : 0));
        dest.writeByte((byte) (chk_taxi ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BenefitsHotelDao> CREATOR = new Creator<BenefitsHotelDao>() {
        @Override
        public BenefitsHotelDao createFromParcel(Parcel in) {
            return new BenefitsHotelDao(in);
        }

        @Override
        public BenefitsHotelDao[] newArray(int size) {
            return new BenefitsHotelDao[size];
        }
    };

    public boolean isChk_internat() {
        return chk_internat;
    }

    public void setChk_internat(boolean chk_internat) {
        this.chk_internat = chk_internat;
    }

    public boolean isChk_shop() {
        return chk_shop;
    }

    public void setChk_shop(boolean chk_shop) {
        this.chk_shop = chk_shop;
    }

    public boolean isChk_dry() {
        return chk_dry;
    }

    public void setChk_dry(boolean chk_dry) {
        this.chk_dry = chk_dry;
    }

    public boolean isChk_parking() {
        return chk_parking;
    }

    public void setChk_parking(boolean chk_parking) {
        this.chk_parking = chk_parking;
    }

    public boolean isChk_taxi() {
        return chk_taxi;
    }

    public void setChk_taxi(boolean chk_taxi) {
        this.chk_taxi = chk_taxi;
    }
}
