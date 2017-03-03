package com.buu.se.searchbangsaen.searchcategories.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;

import com.buu.se.searchbangsaen.searchcategories.adapter.BenefitsAdapter;
import com.buu.se.searchbangsaen.searchcategories.adapter.RecyclerImageAdapter;
import com.buu.se.searchbangsaen.searchcategories.dao.ImageDao;
import com.buu.se.searchbangsaen.searchcategories.dao.RestaurantDao;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class DetailRestaurantActivity extends AppCompatActivity {

    @BindView(R.id.tvNameRes) TextView tvNameRes;
    @BindView(R.id.tvTitle) RelativeLayout tvTitle;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvTypeFood) TextView tvTypeFood;
    @BindView(R.id.tvDate) TextView tvDate;
    @BindView(R.id.rlDetail) RelativeLayout rlDetail;
    @BindView(R.id.tvImg) TextView tvImg;
    @BindView(R.id.rvImgs) RecyclerView rvImgs;
    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.tv_phone) TextView tvPhone;
    @BindView(R.id.tv_location) TextView tvLocation;
    @BindView(R.id.gridview) GridView gridview;

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerImageAdapter mAdapter;
    private RestaurantDao restaurantListDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);
        ButterKnife.bind(this);

        //ส่งค่าจากหน้าร้านอาหารว่าเป็นร้านอาหารไหน
        Intent mIntent = getIntent();
//        int intValue = mIntent.getIntExtra("key", 0);
        //      Toast.makeText(getApplicationContext(), String.valueOf(intValue), Toast.LENGTH_LONG).show();
     //   BenefitsDao Benefit = new BenefitsDao(true,true,true,true,true);



        rvImgs.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvImgs.setLayoutManager(mLayoutManager);
        List<ImageDao> imageList = new ArrayList<>();
        imageList.add(new ImageDao(1, "https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/403484_156009281197295_536660032_n.jpg?oh=a201d4b0399a80cc7535db8f50349334&oe=593FD711"));
        imageList.add(new ImageDao(2, "https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/14364713_894542120677337_1486207317892185568_n.jpg?oh=54a4f740ecd3ad3402d8cb1dec7b60b8&oe=59253DE7"));
        imageList.add(new ImageDao(3, "https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/12994476_815475631917320_5597565369867738509_n.jpg?oh=55d4d4d53a2da7dbd3d3f5a2b30a0601&oe=594514A0"));
        imageList.add(new ImageDao(4, "https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/12523937_811505465647670_6529723795513221531_n.jpg?oh=4c609c39568354fc58b26d050de9b56a&oe=59413864"));
        // imageList.add(new ImageDao(99, ""));
        mAdapter = new RecyclerImageAdapter(DetailRestaurantActivity.this, imageList);
        rvImgs.setAdapter(mAdapter);

        ivBack.setOnClickListener(OnClickBackListener);

        restaurantListDao = mIntent.getParcelableExtra("data");

        gridview.setNumColumns(3);
        gridview.setAdapter(new BenefitsAdapter(this,restaurantListDao.getBenefitsDao()));


        tvName.setText(restaurantListDao.getName());
        tvNameRes.setText(restaurantListDao.getName());
        tvPhone.setText(restaurantListDao.getContact());
        tvLocation.setText(restaurantListDao.getLocation());
    }

    private View.OnClickListener OnClickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
