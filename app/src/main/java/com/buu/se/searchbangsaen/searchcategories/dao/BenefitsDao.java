package com.buu.se.searchbangsaen.searchcategories.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dell on 03/03/2560.
 */

public class BenefitsDao implements Parcelable {
    boolean parking; //มีที่จอดรถ
    boolean creditCards; //รับเคริตการ์ด
    boolean alcohol; //กินเหล้าได้
    boolean Reservation;  //จองล่วงหน้า
    boolean liveMusic; //ดนตรีสด

    int count;

    public BenefitsDao(boolean parking, boolean creditCards, boolean alcohol, boolean reservation, boolean liveMusic) {
        this.parking = parking;
        this.creditCards = creditCards;
        this.alcohol = alcohol;
        this.Reservation = reservation;
        this.liveMusic = liveMusic;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isCreditCards() {
        return creditCards;
    }

    public void setCreditCards(boolean creditCards) {
        this.creditCards = creditCards;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }

    public boolean isReservation() {
        return Reservation;
    }

    public void setReservation(boolean reservation) {
        Reservation = reservation;
    }

    public boolean isLiveMusic() {
        return liveMusic;
    }

    public void setLiveMusic(boolean liveMusic) {
        this.liveMusic = liveMusic;
    }

    public int getSize() {
        this.count = 0;
        if (isParking()) {
            this.count++;
        }
        if (isAlcohol()) {
            this.count++;
        }
        if (isCreditCards()) {
            this.count++;
        }
        if (isReservation()) {
            this.count++;
        }
        if (isLiveMusic()) {
            this.count++;
        }
        return this.count;
    }

    public static Creator<BenefitsDao> getCREATOR() {
        return CREATOR;
    }

    protected BenefitsDao(Parcel in) {
        parking = in.readByte() != 0;
        creditCards = in.readByte() != 0;
        alcohol = in.readByte() != 0;
        Reservation = in.readByte() != 0;
        liveMusic = in.readByte() != 0;
    }

    public static final Creator<BenefitsDao> CREATOR = new Creator<BenefitsDao>() {
        @Override
        public BenefitsDao createFromParcel(Parcel in) {
            return new BenefitsDao(in);
        }

        @Override
        public BenefitsDao[] newArray(int size) {
            return new BenefitsDao[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (parking ? 1 : 0));
        dest.writeByte((byte) (creditCards ? 1 : 0));
        dest.writeByte((byte) (alcohol ? 1 : 0));
        dest.writeByte((byte) (Reservation ? 1 : 0));
        dest.writeByte((byte) (liveMusic ? 1 : 0));
    }
}
