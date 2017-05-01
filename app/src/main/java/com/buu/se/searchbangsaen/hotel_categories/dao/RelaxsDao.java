package com.buu.se.searchbangsaen.hotel_categories.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dell on 26/04/2560.
 */

public class RelaxsDao implements Parcelable {
    private boolean Swim; //สระว่ายน้ำ
    private boolean Fitness; //
    private boolean Playground; //
    private boolean Golf;  //

    public RelaxsDao(Parcel in) {
        Swim = in.readByte() != 0;
        Fitness = in.readByte() != 0;
        Playground = in.readByte() != 0;
        Golf = in.readByte() != 0;
    }

    public static final Creator<RelaxsDao> CREATOR = new Creator<RelaxsDao>() {
        @Override
        public RelaxsDao createFromParcel(Parcel in) {
            return new RelaxsDao(in);
        }

        @Override
        public RelaxsDao[] newArray(int size) {
            return new RelaxsDao[size];
        }
    };

    public RelaxsDao() {

    }

    public boolean isSwim() {
        return Swim;
    }

    public void setSwim(boolean swim) {
        Swim = swim;
    }

    public boolean isFitness() {
        return Fitness;
    }

    public void setFitness(boolean fitness) {
        Fitness = fitness;
    }

    public boolean isPlayground() {
        return Playground;
    }

    public void setPlayground(boolean playground) {
        Playground = playground;
    }

    public boolean isGolf() {
        return Golf;
    }

    public void setGolf(boolean golf) {
        Golf = golf;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (Swim ? 1 : 0));
        dest.writeByte((byte) (Fitness ? 1 : 0));
        dest.writeByte((byte) (Playground ? 1 : 0));
        dest.writeByte((byte) (Golf ? 1 : 0));
    }
}
