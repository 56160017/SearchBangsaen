package com.buu.se.searchbangsaen.restaurant_categories.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.MapDirectionActivity;
import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.dao.TypeResDao;
import com.buu.se.searchbangsaen.restaurant_categories.adapter.BenefitsAdapter;
import com.buu.se.searchbangsaen.restaurant_categories.adapter.RecyclerImageAdapter;
import com.buu.se.searchbangsaen.restaurant_categories.dao.ImageDao;
import com.buu.se.searchbangsaen.restaurant_categories.dao.RestaurantDao;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class DetailRestaurantActivity extends AppCompatActivity {

    @BindView(R.id.tvNameTitle) TextView tvNameRes;

    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvDate) TextView tvDate;
    @BindView(R.id.rlDetail) RelativeLayout rlDetail;
    @BindView(R.id.tvImg) TextView tvImg;
    @BindView(R.id.rvImgs) RecyclerView rvImgs;
    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.tv_phone) TextView tvPhone;
    @BindView(R.id.tv_location) TextView tvLocation;
    @BindView(R.id.gv_benefit) GridView gvBenefit;
    @BindView(R.id.tv_type_food) TextView tvTypeFood;
    @BindView(R.id.btn_map) Button btnMap;
    @BindView(R.id.ivAdd) ImageView ivAdd;
    @BindView(R.id.tvTitle) CardView tvTitle;
    @BindView(R.id.tvTime) TextView tvTime;

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerImageAdapter mAdapter;
    private RestaurantDao restaurantListDao;
    private TypeResDao typeResDao;
    private StorageReference mStorage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);
        ButterKnife.bind(this);


        //ส่งค่าจากหน้าร้านอาหารว่าเป็นร้านอาหารไหน
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("key", 0);
        restaurantListDao = mIntent.getParcelableExtra("data");
        Log.d("onCreatexxx: ", "" + restaurantListDao.getmUri().get(0));
        //image view
        mStorage = FirebaseStorage.getInstance().getReference();
        rvImgs.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvImgs.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerImageAdapter(DetailRestaurantActivity.this, getImageDao(), restaurantListDao.getName());
        rvImgs.setAdapter(mAdapter);

        String text = getStringTypeFood();
        tvTypeFood.setText(text);

        gvBenefit.setNumColumns(3);
        gvBenefit.setAdapter(new BenefitsAdapter(this, restaurantListDao.getBenefitsDao()));

        tvNameRes.setText(restaurantListDao.getName());
        tvName.setText(restaurantListDao.getName());
        tvDate.setText("วันที่เปิด: " + restaurantListDao.getDay());
        tvTime.setText("เวลาทำการ: "+ restaurantListDao.getOpen() + " น. - " + restaurantListDao.getClose() + " น.");
        tvPhone.setText(restaurantListDao.getContact());
        tvLocation.setText(restaurantListDao.getLocation());

        ivBack.setOnClickListener(OnClickBackListener);
        ivAdd.setVisibility(View.INVISIBLE);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailRestaurantActivity.this, MapDirectionActivity.class);
                i.putExtra("name",restaurantListDao.getName());
                i.putExtra("loc",restaurantListDao.getLocation());
                i.putExtra("lat",restaurantListDao.getLatitude());
                i.putExtra("lng",restaurantListDao.getLongitude());

                startActivity(i);
            }
        });
    }

    @NonNull
    private String getStringTypeFood() {
        String text = "";
        if(restaurantListDao.getTypeResDao().isChkSea()){
            text +="อาหารทะเล ";
        }
        if(restaurantListDao.getTypeResDao().isChkSingleFood()){
            text +="อาหารจานเดียว ";
        }
        if(restaurantListDao.getTypeResDao().isChkEsanfood()){
            text +="อาหารอีสาน ";
        }
        if(restaurantListDao.getTypeResDao().isChkThai()){
            text +="อาหารไทย ";
        }
        if(restaurantListDao.getTypeResDao().isChkWildfood()){
            text +="อาหารป่า ";
        }
        if(restaurantListDao.getTypeResDao().isChkBuffet()){
            text +="Buffet ";
        }
        return text;
    }

    @NonNull
    private List<ImageDao> getImageDao() {
        List<ImageDao> imageList = new ArrayList<>();
        for (Uri geturi : restaurantListDao.getmUri()) {
            imageList.add(new ImageDao(1, "" + geturi));
        }
        return imageList;
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
