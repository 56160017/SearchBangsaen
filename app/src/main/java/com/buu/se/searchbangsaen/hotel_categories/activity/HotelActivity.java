package com.buu.se.searchbangsaen.hotel_categories.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.hotel_categories.adapter.CardHotelAdapter;
import com.buu.se.searchbangsaen.hotel_categories.dao.HotelDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HotelActivity extends AppCompatActivity {

    @BindView(R.id.tvTitle) RelativeLayout tvTitle;
    @BindView(R.id.tvNameTitle) TextView tvNameTitle;
    @BindView(R.id.recycler_hotel) RecyclerView recyclerHotel;
    @BindView(R.id.ivBack) ImageView ivBack;

    private RecyclerView.LayoutManager mLayoutManager;
    private CardHotelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        ButterKnife.bind(this);
        ivBack.setOnClickListener(OnClickBackListener);
        setBgPage();

        recyclerHotel.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerHotel.setLayoutManager(mLayoutManager);

        List<String> tfDao = new ArrayList<>();
        tfDao.add("อาหารทะเล");
        tfDao.add("อาหารจานเดียว");
        tfDao.add("อาหารตามสั่ง");
        tfDao.add("อาหารไทย");


        //add befinet
//        BenefitsDao benefDao = new BenefitsDao(true,true,true,true,true);
//        BenefitsDao benefDao2 = new BenefitsDao(false,false,false,false,false);
//        BenefitsDao benefDao3 = new BenefitsDao(false,true,false,true,false);
//        BenefitsDao benefDao4 = new BenefitsDao(true,true,true,true,false);
//        BenefitsDao benefDao5 = new BenefitsDao(true,false,false,false,true);


        List<HotelDao> HotelDaoList = new ArrayList<>();
        HotelDaoList.add(new HotelDao(1, "โคโค่ บีช รีสอร์ท บางแสน", 1, "06-28354459", 2500,1700, "2"));
        HotelDaoList.add(new HotelDao(2, "โรงแรมบางแสน เฮอริเทจ", 4, "087-3377878", 2200,1100, "5"));
        HotelDaoList.add(new HotelDao(2, "เดอะ ไทด์ รีสอร์ทจ", 5, "089-2526163", 4300,2000, "10"));
        mAdapter = new CardHotelAdapter(HotelActivity.this, HotelDaoList);
        recyclerHotel.setAdapter(mAdapter);

    }

    private void setBgPage() {
        tvTitle.setBackgroundColor(getResources().getColor(R.color.title_bar_hotel));
        tvNameTitle.setText("โรงแรม");
        recyclerHotel.setBackgroundColor(getResources().getColor(R.color.background_detail_hotel));
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
