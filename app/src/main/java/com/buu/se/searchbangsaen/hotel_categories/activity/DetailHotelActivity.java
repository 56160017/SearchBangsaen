package com.buu.se.searchbangsaen.hotel_categories.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
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
import com.buu.se.searchbangsaen.hotel_categories.adapter.BenefitshotelAdapter;
import com.buu.se.searchbangsaen.hotel_categories.adapter.RecyclerImageHotelAdapter;
import com.buu.se.searchbangsaen.hotel_categories.adapter.RelaxhotelAdapter;
import com.buu.se.searchbangsaen.hotel_categories.dao.BenefitsHotelDao;
import com.buu.se.searchbangsaen.hotel_categories.dao.HotelDao;
import com.buu.se.searchbangsaen.hotel_categories.dao.RelaxsDao;
import com.buu.se.searchbangsaen.restaurant_categories.adapter.RecyclerImageAdapter;
import com.buu.se.searchbangsaen.restaurant_categories.dao.ImageDao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.gv_relax) GridView gvRelax;
    @BindView(R.id.rvImgs) RecyclerView rvImgs;


    private HotelDao hotelListDao;
    private BenefitsHotelDao benefitsHotelDao;
    private RelaxsDao relaxDao;
    private LinearLayoutManager mLayoutManager;
    private RecyclerImageHotelAdapter mAdapter;

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
//        Log.d("onCreatemuri: ", "" + hotelListDao.getmUri().get(0) + "\n" + hotelListDao.getmUri().get(1));

        tvNameTitle.setText(hotelListDao.getName());
        tvName.setText(hotelListDao.getName());
        tvLocation.setText(hotelListDao.getContact());
        tvEmtryRoom.setText("เหลือ " + hotelListDao.getEmpty_room() + " ห้องสุดท้าย");

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        tvPriceF.setText("THB " + formatter.format(Integer.parseInt(hotelListDao.getPrice_f())));
        tvPriceF.setPaintFlags(tvPriceF.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        tvPriceT.setText("THB " + formatter.format(Integer.parseInt(hotelListDao.getPrice_t())));


        setBenefit();

        relaxDao = new RelaxsDao();
        relaxDao.setSwim(hotelListDao.getRelaxDao().isSwim());
        relaxDao.setFitness(hotelListDao.getRelaxDao().isFitness());
        relaxDao.setPlayground(hotelListDao.getRelaxDao().isPlayground());
        relaxDao.setGolf(hotelListDao.getRelaxDao().isGolf());

        gvRelax.setNumColumns(3);
        gvRelax.setAdapter(new RelaxhotelAdapter(this, relaxDao));

        //set image
        rvImgs.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvImgs.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerImageHotelAdapter(DetailHotelActivity.this, getImageDao(), hotelListDao.getName());
        rvImgs.setAdapter(mAdapter);

        btnMap.setOnClickListener(OnClickMapListener);
    }

    private List<ImageDao> getImageDao() {
        List<ImageDao> imageList = new ArrayList<>();
        for (Uri geturi : hotelListDao.getmUri()) {
            imageList.add(new ImageDao(1, "" + geturi));
        }
        return imageList;
    }

    private void setBenefit() {
        benefitsHotelDao = new BenefitsHotelDao();
        benefitsHotelDao.setChk_internat(hotelListDao.getBenefitHotelDao().isChk_internat());
        benefitsHotelDao.setChk_dry(hotelListDao.getBenefitHotelDao().isChk_dry());
        benefitsHotelDao.setChk_shop(hotelListDao.getBenefitHotelDao().isChk_shop());
        benefitsHotelDao.setChk_parking(hotelListDao.getBenefitHotelDao().isChk_parking());
        benefitsHotelDao.setChk_taxi(hotelListDao.getBenefitHotelDao().isChk_taxi());
        gvBenefit.setNumColumns(3);
        gvBenefit.setAdapter(new BenefitshotelAdapter(this, benefitsHotelDao));
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
            i.putExtra("name", hotelListDao.getName());
            i.putExtra("loc", hotelListDao.getContact());
            i.putExtra("lat", hotelListDao.getLatitude());
            i.putExtra("lng", hotelListDao.getLongitude());
            startActivity(i);
        }
    };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
