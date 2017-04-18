package com.buu.se.searchbangsaen.hotel_categories.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.activity.AddHotelActivity;
import com.buu.se.searchbangsaen.auth.activity.AuthActivity;
import com.buu.se.searchbangsaen.hotel_categories.adapter.CardHotelAdapter;
import com.buu.se.searchbangsaen.hotel_categories.dao.HotelDao;
import com.buu.se.searchbangsaen.restaurant_categories.dao.RestaurantDao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HotelActivity extends AppCompatActivity {


    @BindView(R.id.tvNameTitle) TextView tvNameTitle;
    @BindView(R.id.recycler_hotel) RecyclerView recyclerHotel;
    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.tvTitle) CardView tvTitle;
    @BindView(R.id.ivAdd) ImageView ivAdd;

    private RecyclerView.LayoutManager mLayoutManager;
    private CardHotelAdapter mAdapter;

    private List<HotelDao> HotelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        ButterKnife.bind(this);
        ivBack.setOnClickListener(OnClickBackListener);
        ivAdd.setOnClickListener(OnClickAddListener);
        setBgPage();

        recyclerHotel.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerHotel.setLayoutManager(mLayoutManager);

        List<String> tfDao = new ArrayList<>();
        tfDao.add("อาหารทะเล");
        tfDao.add("อาหารจานเดียว");
        tfDao.add("อาหารตามสั่ง");
        tfDao.add("อาหารไทย");


        /*List<HotelDao> HotelDaoList = new ArrayList<>();
        HotelDaoList.add(new HotelDao(1, "โคโค่ บีช รีสอร์ท บางแสน", "1", "06-28354459", "2500", "1700", "2"));
        HotelDaoList.add(new HotelDao(2, "โรงแรมบางแสน เฮอริเทจ", "4", "087-3377878", "2200", "1100", "5"));
        HotelDaoList.add(new HotelDao(2, "เดอะ ไทด์ รีสอร์ทจ", "5", "089-2526163", "4300", "2000", "10"));
        mAdapter = new CardHotelAdapter(HotelActivity.this, HotelDaoList);
        recyclerHotel.setAdapter(mAdapter);*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        initRestaurantData();
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
    View.OnClickListener   OnClickAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(HotelActivity.this,AuthActivity.class);
            i.putExtra("CategoriesID", 2);
            startActivity(i);
        }
    };

    private void initRestaurantData() {

        HotelList = new ArrayList<>();
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        Query query = mRootRef.child("hotel");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    //Log.d("onDataChange: ","test");
                    // dataSnapshot is the "issue" node with all children with id 0

                    for (DataSnapshot uuid : dataSnapshot.getChildren()) {
                        Log.d("onDataChangeID: ",""+uuid.getKey());
                        HotelDao a = new HotelDao();
                        for (DataSnapshot getuuid : uuid.getChildren()){

                            switch (getuuid.getKey()){
                                case "name":
                                    a.setName("" + getuuid.getValue());
                                    Log.d("onDataChangeName: ",""+getuuid.getValue());
                                    break;
                                case "phone": a.setPhone("" + getuuid.getValue());
                                    Log.d("onDataChangePhone: ",""+getuuid.getValue());
                                    break;
                                case "emtry_room": a.setEmpty_room("" + getuuid.getValue());
                                    break;
                                case "price_f": a.setPrice_f("" + getuuid.getValue());
                                    break;
                                case "price_t": a.setPrice_t("" + getuuid.getValue());
                                    break;
                                case "latitude":  a.setLatitude(Double.parseDouble("" + getuuid.getValue()));
                                    break;
                                case "longitude": a.setLongitude(Double.parseDouble("" + getuuid.getValue()));
                                    break;
                                case "address": a.setContact("" + getuuid.getValue());
                                    break;
                                default: break;
                            }
                            Log.d("onDataChange: ",""+getuuid.getValue());
                        }
                        //String timeStamp = new SimpleDateFormat("HH.mm.ss").format(Calendar.getInstance().getTime())
                        HotelList.add(a);

                    }

                    mAdapter = new CardHotelAdapter(HotelActivity.this, HotelList);
                    recyclerHotel.setAdapter(mAdapter);
                  //  mapFragment.getMapAsync(RestaurantSearchActivity.this);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
