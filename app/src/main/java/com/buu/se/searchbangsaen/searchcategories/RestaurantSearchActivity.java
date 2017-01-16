package com.buu.se.searchbangsaen.searchcategories;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.buu.se.searchbangsaen.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantSearchActivity extends AppCompatActivity {

    @BindView(R.id.recycler_res) RecyclerView recyclerRes;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerRestaurantSearch mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);
        ButterKnife.bind(this);
        recyclerRes.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerRes.setLayoutManager(mLayoutManager);
       // mAdapter = new RecyclerRestaurantSearch(RestaurantSearchActivity.this);
        List<RestaurantDao> restaurantList = new ArrayList<>();
        restaurantList.add(new RestaurantDao(1,"LOBSTER BUCKET","จันทร - ศุกร์","16.00","24.00","06-28354459","OPEN","2"));
        restaurantList.add(new RestaurantDao(2, "ชาบูชิลล์ ณ บางแสน", "จันทร์ - พฤหัสบดี", "17.00", "23.30", "086-1013756", "OPEN", "3"));
        restaurantList.add(new RestaurantDao(3, "ครัวต้นข้าว", "พุธ - อาทิตย์", "15.00", "22.00", "089-2526163", "OPEN", "5.2"));
        restaurantList.add(new RestaurantDao(5, "กุ้งถัง บางแสน", "อังคาร - ศุกร์", "06.00", "16.00", "098-9894337", "OPEN", "6"));
        restaurantList.add(new RestaurantDao(4, "มงคลฟาร์ม บางแสน", "จันทร์ - เสาร์", "10.00", "22.00", "081-3993833", "OPEN", "9.4"));

       // ListNotiAdapter listNotiAdapter = new ListNotiAdapter(getActivity(),userNotiDaoList);
        mAdapter = new RecyclerRestaurantSearch(RestaurantSearchActivity.this,restaurantList);
        recyclerRes.setAdapter(mAdapter);


    }
}
