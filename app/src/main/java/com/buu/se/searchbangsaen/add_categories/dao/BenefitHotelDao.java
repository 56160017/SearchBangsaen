package com.buu.se.searchbangsaen.add_categories.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dell on 26/04/2560.
 */

public class BenefitHotelDao implements Parcelable {
    private boolean chkInternat; //
    private boolean chkShop; //
    private boolean chkDry; //
    private boolean chkParking;  //
    private boolean chkTaxi; //

    public BenefitHotelDao(Parcel in) {
        chkInternat = in.readByte() != 0;
        chkShop = in.readByte() != 0;
        chkDry = in.readByte() != 0;
        chkParking = in.readByte() != 0;
        chkTaxi = in.readByte() != 0;
    }

    public static final Creator<BenefitHotelDao> CREATOR = new Creator<BenefitHotelDao>() {
        @Override
        public BenefitHotelDao createFromParcel(Parcel in) {
            return new BenefitHotelDao(in);
        }

        @Override
        public BenefitHotelDao[] newArray(int size) {
            return new BenefitHotelDao[size];
        }
    };

    public BenefitHotelDao() {

    }

    public boolean isChkInternat() {
        return chkInternat;
    }

    public void setChkInternat(boolean chkInternat) {
        this.chkInternat = chkInternat;
    }

    public boolean isChkShop() {
        return chkShop;
    }

    public void setChkShop(boolean chkShop) {
        this.chkShop = chkShop;
    }

    public boolean isChkDry() {
        return chkDry;
    }

    public void setChkDry(boolean chkDry) {
        this.chkDry = chkDry;
    }

    public boolean isChkParking() {
        return chkParking;
    }

    public void setChkParking(boolean chkParking) {
        this.chkParking = chkParking;
    }

    public boolean isChkTaxi() {
        return chkTaxi;
    }

    public void setChkTaxi(boolean chkTaxi) {
        this.chkTaxi = chkTaxi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (chkInternat ? 1 : 0));
        dest.writeByte((byte) (chkShop ? 1 : 0));
        dest.writeByte((byte) (chkDry ? 1 : 0));
        dest.writeByte((byte) (chkParking ? 1 : 0));
        dest.writeByte((byte) (chkTaxi ? 1 : 0));
    }
}
