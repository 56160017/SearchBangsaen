package com.buu.se.searchbangsaen.add_categories.dao;

/**
 * Created by Dell on 15/04/2560.
 */

public class BenefitDao {
    private boolean parking; //มีที่จอดรถ
    private boolean creditCards; //รับเคริตการ์ด
    private boolean alcohol; //กินเหล้าได้
    private boolean Reservation;  //จองล่วงหน้า
    private boolean liveMusic; //ดนตรีสด

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
}
