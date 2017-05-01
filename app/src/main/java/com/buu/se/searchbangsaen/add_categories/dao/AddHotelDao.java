package com.buu.se.searchbangsaen.add_categories.dao;

import com.buu.se.searchbangsaen.hotel_categories.dao.BenefitsHotelDao;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Dell on 14/04/2560.
 */

public class AddHotelDao {
    private String nameHotel;
    private String hotelPhone;
    private String hotelEmtryRoom;
    private String hotelPrice_f;
    private String hotelPrice_t;
    private String hotelAddress;
    private LatLng hotelLatLng;
    private RelaxDao relaxDao;
    private BenefitHotelDao addbenefitDao;

    public RelaxDao getRelaxDao() {
        return relaxDao;
    }

    public void setRelaxDao(RelaxDao relaxDao) {
        this.relaxDao = relaxDao;
    }

    public BenefitHotelDao getBenefitHotelDao() {
        return addbenefitDao;
    }

    public void setBenefitHotelDao(BenefitHotelDao addbenefitDao) {
        this.addbenefitDao = addbenefitDao;
    }

    public String getNameHotel() {
        return nameHotel;
    }

    public void setNameHotel(String nameHotel) {
        this.nameHotel = nameHotel;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getHotelEmtryRoom() {
        return hotelEmtryRoom;
    }

    public void setHotelEmtryRoom(String hotelEmtryRoom) {
        this.hotelEmtryRoom = hotelEmtryRoom;
    }

    public String getHotelPrice_f() {
        return hotelPrice_f;
    }

    public void setHotelPrice_f(String hotelPrice_f) {
        this.hotelPrice_f = hotelPrice_f;
    }

    public String getHotelPrice_t() {
        return hotelPrice_t;
    }

    public void setHotelPrice_t(String hotelPrice_t) {
        this.hotelPrice_t = hotelPrice_t;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String resAddress) {
        this.hotelAddress = resAddress;
    }

    public LatLng getHotelLatLng() {
        return hotelLatLng;
    }

    public void setHotelLatLng(LatLng resLatLng) {
        this.hotelLatLng = resLatLng;
    }
}
