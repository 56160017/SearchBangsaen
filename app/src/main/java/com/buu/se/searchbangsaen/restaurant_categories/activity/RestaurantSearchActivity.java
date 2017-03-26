package com.buu.se.searchbangsaen.restaurant_categories.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.buu.se.searchbangsaen.auth.activity.AuthActivity;
import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.restaurant_categories.adapter.CardRestaurantSearchAdapter;
import com.buu.se.searchbangsaen.restaurant_categories.dao.BenefitsDao;
import com.buu.se.searchbangsaen.restaurant_categories.dao.RestaurantDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RestaurantSearchActivity extends AppCompatActivity {

    @BindView(R.id.recycler_res) RecyclerView recyclerRes;
    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.ivAdd) ImageView ivAdd;


    private RecyclerView.LayoutManager mLayoutManager;
    private CardRestaurantSearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);
        ButterKnife.bind(this);


        recyclerRes.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerRes.setLayoutManager(mLayoutManager);

        List<String> tfDao = new ArrayList<>();
        tfDao.add("อาหารทะเล");
        tfDao.add("อาหารจานเดียว");
        tfDao.add("อาหารตามสั่ง");
        tfDao.add("อาหารไทย");


        //add befinet
        BenefitsDao benefDao = new BenefitsDao(true, true, true, true, true);
        BenefitsDao benefDao2 = new BenefitsDao(false, false, false, false, false);
        BenefitsDao benefDao3 = new BenefitsDao(false, true, false, true, false);
        BenefitsDao benefDao4 = new BenefitsDao(true, true, true, true, false);
        BenefitsDao benefDao5 = new BenefitsDao(true, false, false, false, true);

        //add data
        List<RestaurantDao> restaurantList = new ArrayList<>();
        restaurantList.add(new RestaurantDao(1, "ครัวน้องเบสท์", "จันทร์ - ศุกร์", "10.00", "22.00", "06-28354459", "27/1 บางแสนสาย 4 เหนือ ต.แสนสุข อ.เมือง จ.ชลบุรี", "OPEN", "2", tfDao, benefDao));
        restaurantList.add(new RestaurantDao(2, "ชาบูบู๊ตึ๊ง", "จันทร์ - พฤหัสบดี", "10.30", "22.30", "087-3377878", "19/15 บางแสนสาย 4 ใต้ ต.แสนสุข อ อ.เมือง จ.ชลบุรี", "OPEN", "3", tfDao, benefDao2));
        restaurantList.add(new RestaurantDao(3, "ลิงลงเรือ", "พุธ - อาทิตย์", "08.00", "19.00", "089-2526163", "18/1 ถ.วชิรปราการ ต.บางปลาสร้อย อ.เมือง จ.ชลบุรี", "OPEN", "5.2", tfDao, benefDao3));
        restaurantList.add(new RestaurantDao(4, "ข้าวต้มแสนสุข", "ทุกวัน", "18.00", "05.00", "086-545-6416", "398 ถนนลงหาดบางแสน (ร้านอยู่ทางลงหาดบางแสนด้านขวามือ ตรงข้ามกับหอพัก สิมิลัน)", "OPEN", "6", tfDao, benefDao4));
        restaurantList.add(new RestaurantDao(5, "ก๋วยเตี๋ยวไง บางแสน", "จันทร์ - เสาร์", "11.30", "20.00", "093-9655565 ", "ก่อนถึง ม.บูรพา เจอร้าน ม.หมูกะทะ เลี้ยวซ้ายไปประมาณ 100 เมตรร้านอยู่ขวามือ", "OPEN", "9.4", tfDao, benefDao5));

        mAdapter = new CardRestaurantSearchAdapter(RestaurantSearchActivity.this, restaurantList);
        recyclerRes.setAdapter(mAdapter);
        ivBack.setOnClickListener(OnClickBackListener);
        //  ivMenu.setOnClickListener(OnClickMenuListener);
        ivAdd.setOnClickListener(OnClickAddBackListener);
    }

    private View.OnClickListener OnClickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };
    private View.OnClickListener OnClickAddBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(RestaurantSearchActivity.this, AuthActivity.class);
            i.putExtra("CategoriesID","restaurant");
            startActivity(i);
        }
    };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
