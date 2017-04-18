package com.buu.se.searchbangsaen.hotel_categories.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.MapDirectionActivity;
import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.hotel_categories.adapter.BenefitshotelAdapter;
import com.buu.se.searchbangsaen.hotel_categories.dao.BenefitsHotelDao;
import com.buu.se.searchbangsaen.hotel_categories.dao.HotelDao;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailHotelActivity extends AppCompatActivity {

    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.tvNameTitle) TextView tvNameTitle;

    @BindView(R.id.activity_detail_hotel) RelativeLayout activityDetailHotel;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvTitle) CardView tvTitle;
    @BindView(R.id.ivAdd) ImageView ivAdd;
    @BindView(R.id.gv_benefit) GridView gvBenefit;
    @BindView(R.id.tv_location) TextView tvLocation;
    @BindView(R.id.btn_map) Button btnMap;
    @BindView(R.id.tv_emtry_room) TextView tvEmtryRoom;
    @BindView(R.id.tv_price_f) TextView tvPriceF;
    @BindView(R.id.tv_price_t) TextView tvPriceT;


    private HotelDao hotelListDao;
    private BenefitsHotelDao benefitsHotelDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        ButterKnife.bind(this);
        setBgPage();
        ivAdd.setVisibility(View.GONE);
        ivBack.setOnClickListener(OnClickBackListener);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("key", 0);
        hotelListDao = mIntent.getParcelableExtra("data");

        tvNameTitle.setText(hotelListDao.getName());
        tvName.setText(hotelListDao.getName());
        tvLocation.setText(hotelListDao.getContact());
        tvEmtryRoom.setText("เหลือ " + hotelListDao.getEmpty_room() + " ห้องสุดท้าย");

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        tvPriceF.setText("THB " + formatter.format(Integer.parseInt(hotelListDao.getPrice_f())));
        tvPriceF.setPaintFlags(tvPriceF.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        tvPriceT.setText("THB " + formatter.format(Integer.parseInt(hotelListDao.getPrice_t())));


        benefitsHotelDao = new BenefitsHotelDao();
        benefitsHotelDao.setChk_internat(true);
        benefitsHotelDao.setChk_dry(true);
        benefitsHotelDao.setChk_shop(true);
        benefitsHotelDao.setChk_parking(false);
        benefitsHotelDao.setChk_taxi(true);

        gvBenefit.setNumColumns(3);
        gvBenefit.setAdapter(new BenefitshotelAdapter(this, benefitsHotelDao));


        btnMap.setOnClickListener(OnClickMapListener);
    }

    private void setBgPage() {
        tvTitle.setBackgroundColor(getResources().getColor(R.color.title_bar_hotel));
        tvNameTitle.setText("โรงแรม");
        activityDetailHotel.setBackgroundColor(getResources().getColor(R.color.background_detail_hotel));
    }

    View.OnClickListener OnClickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };

    private View.OnClickListener OnClickMapListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(DetailHotelActivity.this, MapDirectionActivity.class);
            i.putExtra("name",hotelListDao.getName());
            i.putExtra("loc",hotelListDao.getContact());
            i.putExtra("lat",hotelListDao.getLatitude());
            i.putExtra("lng",hotelListDao.getLongitude());
            startActivity(i);
        }
    };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
