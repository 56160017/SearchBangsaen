package com.buu.se.searchbangsaen.add_categories.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dell on 15/04/2560.
 */

public class TypeResDao implements Parcelable {
    private boolean chkSea;
    private boolean chkSingleFood;
    private boolean chkThai;
    private boolean chkWildfood;
    private boolean chkEsanfood;
    private boolean chkBuffet;

    int count;


    public TypeResDao() {


    }

    protected TypeResDao(Parcel in) {
        chkSea = in.readByte() != 0;
        chkSingleFood = in.readByte() != 0;
        chkThai = in.readByte() != 0;
        chkWildfood = in.readByte() != 0;
        chkEsanfood = in.readByte() != 0;
        chkBuffet = in.readByte() != 0;
        count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (chkSea ? 1 : 0));
        dest.writeByte((byte) (chkSingleFood ? 1 : 0));
        dest.writeByte((byte) (chkThai ? 1 : 0));
        dest.writeByte((byte) (chkWildfood ? 1 : 0));
        dest.writeByte((byte) (chkEsanfood ? 1 : 0));
        dest.writeByte((byte) (chkBuffet ? 1 : 0));
        dest.writeInt(count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TypeResDao> CREATOR = new Creator<TypeResDao>() {
        @Override
        public TypeResDao createFromParcel(Parcel in) {
            return new TypeResDao(in);
        }

        @Override
        public TypeResDao[] newArray(int size) {
            return new TypeResDao[size];
        }
    };

    public boolean isChkSea() {
        return chkSea;
    }

    public void setChkSea(Boolean chkSea) {
        this.chkSea = chkSea;
    }

    public boolean isChkSingleFood() {
        return chkSingleFood;
    }

    public void setChkSingleFood(Boolean chkSingleFood) {
        this.chkSingleFood = chkSingleFood;
    }

    public boolean isChkThai() {
        return chkThai;
    }

    public void setChkThai(Boolean chkThai) {
        this.chkThai = chkThai;
    }

    public boolean isChkWildfood() {
        return chkWildfood;
    }

    public void setChkWildfood(Boolean chkWildfood) {
        this.chkWildfood = chkWildfood;
    }

    public boolean isChkEsanfood() {
        return chkEsanfood;
    }

    public void setChkEsanfood(Boolean chkEsanfood) {
        this.chkEsanfood = chkEsanfood;
    }

    public boolean isChkBuffet() {
        return chkBuffet;
    }

    public void setChkBuffet(Boolean chkBuffet) {
        this.chkBuffet = chkBuffet;
    }

    public int getSize() {
        this.count = 0;
        if (isChkSea()) {
            this.count++;
        }
        if (isChkSingleFood()) {
            this.count++;
        }
        if (isChkThai()) {
            this.count++;
        }
        if (isChkWildfood()) {
            this.count++;
        }
        if (isChkEsanfood()) {
            this.count++;
        }
        if (isChkBuffet()) {
            this.count++;
        }
        return this.count;
    }


}
