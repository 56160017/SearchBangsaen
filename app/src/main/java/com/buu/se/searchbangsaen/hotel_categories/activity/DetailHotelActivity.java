package com.buu.se.searchbangsaen.hotel_categories.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.hotel_categories.dao.HotelDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailHotelActivity extends AppCompatActivity {

    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.tvNameTitle) TextView tvNameTitle;
    @BindView(R.id.tvTitle) RelativeLayout tvTitle;
    @BindView(R.id.activity_detail_hotel) RelativeLayout activityDetailHotel;
    @BindView(R.id.tvName) TextView tvName;


    private HotelDao hotelListDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        ButterKnife.bind(this);
        setBgPage();
        ivBack.setOnClickListener(OnClickBackListener);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("key", 0);
        hotelListDao = mIntent.getParcelableExtra("data");

        tvNameTitle.setText(hotelListDao.getName());
        tvName.setText(hotelListDao.getName());

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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
