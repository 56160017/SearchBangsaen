package com.buu.se.searchbangsaen.restaurant_categories.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.constant.Unit;
import com.akexorcist.googledirection.model.Direction;
import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.dao.DateDao;
import com.buu.se.searchbangsaen.add_categories.dao.TypeResDao;
import com.buu.se.searchbangsaen.auth.activity.AuthActivity;
import com.buu.se.searchbangsaen.restaurant_categories.adapter.CardRestaurantSearchAdapter;
import com.buu.se.searchbangsaen.restaurant_categories.dao.BenefitsDao;
import com.buu.se.searchbangsaen.restaurant_categories.dao.RestaurantDao;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RestaurantSearchActivity extends AppCompatActivity implements OnMapReadyCallback, DirectionCallback, GoogleMap.OnMyLocationChangeListener {

    @BindView(R.id.recycler_res) RecyclerView recyclerRes;
    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.ivAdd) ImageView ivAdd;



    private RecyclerView.LayoutManager mLayoutManager;
    private CardRestaurantSearchAdapter mAdapter;

    //map
    private GoogleMap mMap;
    private String serverkey;
    private GoogleApiClient googleApiClient;
    private Location location;
    private String Dir;

    private List<RestaurantDao> restaurantList;

    //FirebaseAuth
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog progressDialog;
    private StorageReference mStorage; //test
    private SupportMapFragment mapFragment;

    public RestaurantSearchActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(RestaurantSearchActivity.this);
        progressDialog.show();


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        recyclerRes.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerRes.setLayoutManager(mLayoutManager);

        //add data with firebase
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

         /*   restaurantList.add(new RestaurantDao(1, "ครัวน้องเบสท์", "จันทร์ - ศุกร์", "10.00", "22.00", "06-28354459", "27/1 บางแสนสาย 4 เหนือ ต.แสนสุข อ.เมือง จ.ชลบุรี", "OPEN", "2", 13.290641, 100.926289, tfDao, benefDao));
        restaurantList.add(new RestaurantDao(2, "ชาบูบู๊ตึ๊ง", "จันทร์ - พฤหัสบดี", "10.30", "22.30", "087-3377878", "19/15 บางแสนสาย 4 ใต้ ต.แสนสุข อ อ.เมือง จ.ชลบุรี", "OPEN", "3", 13.283835, 100.928929, tfDao, benefDao2));
        restaurantList.add(new RestaurantDao(3, "เตี๋ยวลิงลงเรือ", "พุธ - อาทิตย์", "08.00", "19.00", "089-2526163", "18/1 ถ.วชิรปราการ ต.บางปลาสร้อย อ.เมือง จ.ชลบุรี", "OPEN", "5.2", 13.357047, 100.982697, tfDao, benefDao3));
        restaurantList.add(new RestaurantDao(4, "ก๋วยเตี๋ยวไง บางแสน", "จันทร์ - เสาร์", "11.30", "20.00", "093-9655565 ", "ก่อนถึง ม.บูรพา เจอร้าน ม.หมูกะทะ เลี้ยวซ้ายไปประมาณ 100 เมตรร้านอยู่ขวามือ", "OPEN", "9.4", 13.285760, 100.931292, tfDao, benefDao5));
        RestaurantDao test = new RestaurantDao(5, "ก๋วยเตี๋ยวไง บางแสน", "จันทร์ - เสาร์", "11.30", "20.00", "093-9655565 ", "ก่อนถึง ม.บูรพา เจอร้าน ม.หมูกะทะ เลี้ยวซ้ายไปประมาณ 100 เมตรร้านอยู่ขวามือ", "OPEN", "9.4", 13.285760, 100.931292, tfDao, benefDao5);
        restaurantList.add(test);*/
        ivBack.setOnClickListener(OnClickBackListener);
        ivAdd.setOnClickListener(OnClickAddBackListener);

        serverkey = getResources().getString(R.string.google_maps_key);
         mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fake);

    }

    @Override
    protected void onResume() {
        super.onResume();
        CheckAuth();
        initRestaurantData();
        progressDialog.hide();
    }
    private void initRestaurantData() {



        /*final List<String> tfDao = new ArrayList<>();
        tfDao.add("อาหารทะเล");
        tfDao.add("อาหารจานเดียว");
        tfDao.add("อาหารตามสั่ง");
        tfDao.add("อาหารไทย");*/
        //add befinet
    /*    final BenefitsDao benefDao = new BenefitsDao(true, true, true, true, true);
        BenefitsDao benefDao2 = new BenefitsDao(false, false, false, false, false);
        BenefitsDao benefDao3 = new BenefitsDao(false, true, false, true, false);
        BenefitsDao benefDao5 = new BenefitsDao(true, false, false, false, true);
*/
        restaurantList = new ArrayList<>();
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        Query query = mRootRef.child("restaurant");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
                                                 @Override
                                                 public void onDataChange(DataSnapshot dataSnapshot) {

                                                     if (dataSnapshot.exists()) {
                                                         //Log.d("onDataChange: ","test");
                                                         // dataSnapshot is the "issue" node with all children with id 0
                                                         int i=0;

                                                         for (DataSnapshot uuid : dataSnapshot.getChildren()) {
                                                             Log.d("onDataChangeID: ",""+uuid.getKey());
                                                             RestaurantDao a = new RestaurantDao();
                                                             BenefitsDao benefitdaos  = new BenefitsDao();
                                                             TypeResDao typeResDaos = new TypeResDao();
                                                             DateDao dateDaos = new DateDao();


                                                             i++;
                                                             for (DataSnapshot getuuid : uuid.getChildren()){

                                                                 a.setId(i);
                                                                 a.setDistance("2");
                                                            //     a.setTfDao(tfDao);
                                                           //      a.setBenefitsDao(benefDao);
                                                                 switch (getuuid.getKey()){
                                                                     case "name":
                                                                         a.setName("" + getuuid.getValue());
                                                                         break;
                                                                     case "phone": a.setContact("" + getuuid.getValue());
                                                                         break;
                                                                     case "time-open": a.setOpen("" + getuuid.getValue());
                                                                         break;
                                                                     case "time-close": a.setClose("" + getuuid.getValue());
                                                                         break;
                                                                     case "dates-open": a.setDay("" + getuuid.getValue());
                                                                         break;
                                                                     case "latitude":  a.setLatitude(Double.parseDouble("" + getuuid.getValue()));
                                                                         break;
                                                                     case "longitude": a.setLongitude(Double.parseDouble("" + getuuid.getValue()));
                                                                         break;
                                                                     case "address": a.setLocation("" + getuuid.getValue());
                                                                         break;
                                                                     case "benefits":
                                                                         for (DataSnapshot getbenefits : getuuid.getChildren()){
                                                                             switch (getbenefits.getKey()) {
                                                                                 case "parking": benefitdaos.setParking(stringToBool("" + getbenefits.getValue()));
                                                                                     break;
                                                                                 case "alcohol": benefitdaos.setAlcohol(stringToBool("" + getbenefits.getValue()));
                                                                                     break;
                                                                                 case "creditcards": benefitdaos.setCreditCards(stringToBool("" + getbenefits.getValue()));
                                                                                     break;
                                                                                 case "livemusic": benefitdaos.setLiveMusic(stringToBool("" + getbenefits.getValue()));
                                                                                     break;
                                                                                 case "reservation": benefitdaos.setReservation(stringToBool("" + getbenefits.getValue()));
                                                                                     break;
                                                                                 default: break;
                                                                             }
                                                                         }
                                                                         a.setBenefitsDao(benefitdaos);
                                                                         break;
                                                                     case "dates":
                                                                         for (DataSnapshot getdates : getuuid.getChildren()){
                                                                             switch (getdates.getKey()) {
                                                                                 case "sunday": dateDaos.setSun(stringToBool("" + getdates.getValue()));
                                                                                     break;
                                                                                 case "monday": dateDaos.setMonday(stringToBool("" + getdates.getValue()));
                                                                                     break;
                                                                                 case "tuesday": dateDaos.setTuesday(stringToBool("" + getdates.getValue()));
                                                                                     break;
                                                                                 case "wednesday": dateDaos.setWed(stringToBool("" + getdates.getValue()));
                                                                                     break;
                                                                                 case "thursday": dateDaos.setThursday(stringToBool("" + getdates.getValue()));
                                                                                     break;
                                                                                 case "friday": dateDaos.setFriday(stringToBool("" + getdates.getValue()));
                                                                                     break;
                                                                                 case "saturday": dateDaos.setSaturday(stringToBool("" + getdates.getValue()));
                                                                                     break;
                                                                                 default: break;
                                                                             }
                                                                        //     a.setDateDao(dateDaos);ยังไม่ได้สร้าง
                                                                         }
                                                                         break;
                                                                     case "typefoods":
                                                                         for (DataSnapshot gettypefoods : getuuid.getChildren()){
                                                                             switch (gettypefoods.getKey()) {
                                                                                 case "buffet": typeResDaos.setChkBuffet(stringToBool("" + gettypefoods.getValue()));
                                                                                     Log.d("test-"+ gettypefoods.getKey(), ""+ typeResDaos.isChkBuffet());
                                                                                     break;
                                                                                 case "esan-food": typeResDaos.setChkEsanfood(stringToBool("" + gettypefoods.getValue()));
                                                                                     Log.d("test-"+ gettypefoods.getKey(), ""+ typeResDaos.isChkEsanfood());
                                                                                     break;
                                                                                 case "sea-food": typeResDaos.setChkSea(stringToBool("" + gettypefoods.getValue()));
                                                                                     Log.d("pa"+ gettypefoods.getKey(), ""+ gettypefoods.getValue());
                                                                                     break;
                                                                                 case "single-food": typeResDaos.setChkSingleFood(stringToBool("" + gettypefoods.getValue()));
                                                                                     Log.d("pa"+ gettypefoods.getKey(), ""+ gettypefoods.getValue());
                                                                                     break;
                                                                                 case "thai-food": typeResDaos.setChkThai(stringToBool("" + gettypefoods.getValue()));
                                                                                     Log.d("pa"+ gettypefoods.getKey(), ""+ gettypefoods.getValue());
                                                                                     break;
                                                                                 case "wild-food": typeResDaos.setChkWildfood(stringToBool("" + gettypefoods.getValue()));
                                                                                     Log.d("pa"+ gettypefoods.getKey(), ""+ gettypefoods.getValue());
                                                                                     break;
                                                                                 default: break;
                                                                             }

                                                                         }
                                                                         a.setTypeResDao(typeResDaos);
                                                                         break;

                                                                     default: break;
                                                                 }
                                                                 Log.d("onDataChange: ",""+getuuid.getValue());
                                                             }
                                                             //String timeStamp = new SimpleDateFormat("HH.mm.ss").format(Calendar.getInstance().getTime());
                                                             String timeStamp = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
                                                             if(Integer.parseInt(a.getOpen().substring(0,2)) <= Integer.parseInt(timeStamp) &&
                                                                     Integer.parseInt(a.getClose().substring(0,2)) > Integer.parseInt(timeStamp) ){

                                                                 Log.d( "CheckDate: ","open");
                                                                 a.setStatus(true);
                                                             }else{
                                                                 Log.d( "CheckDate: ","close");
                                                                 a.setStatus(false);
                                                             }
                                                             Log.d("onDataChangeaName: ",""+a.getName());

                                                             restaurantList.add(a);


                                                         }


                                                         mAdapter = new CardRestaurantSearchAdapter(RestaurantSearchActivity.this, restaurantList);
                                                         recyclerRes.setAdapter(mAdapter);
                                                         mapFragment.getMapAsync(RestaurantSearchActivity.this);
                                                     }
                                                 }

                                                 @Override
                                                 public void onCancelled(DatabaseError databaseError) {

                                                 }
                                             });

    }

    public static boolean stringToBool(String s) {
        if (s.equals("true"))
            return true;
        if (s.equals("false"))
            return false;
        throw new IllegalArgumentException(s+" is not a bool. Only 1 and 0 are.");
    }

    private void CheckAuth() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d("CheckAuth: ", user.getUid());

        } else {

        }
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
            i.putExtra("CategoriesID", 1);
            startActivity(i);

        }
    };

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        Log.d("park:DirectionSuccess: ", direction.getStatus());
        Log.d("park:DirectionSuccess: ", " " + direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());
        Dir = direction.getRouteList().get(0).getLegList().get(0).getDistance().getText();
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Log.d("onDirectionSuccess: ", "bad");
    }


    private void requestDirection(LatLng current, LatLng destination) {
        GoogleDirection.withServerKey(serverkey)
                .from(current)
                .to(destination)
                .transportMode(TransportMode.DRIVING)
                .unit(Unit.METRIC)
                .execute(RestaurantSearchActivity.this);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public void onMyLocationChange(Location location) {

        final LatLng FindLoc = new LatLng(13.281169, 100.936234);
        final LatLng myLoc = new LatLng(location.getLatitude(), location.getLongitude());
        requestDirection(myLoc, FindLoc);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("getItemCount2: ",""+ mAdapter.getItemCount());
       mMap.setMyLocationEnabled(true);
       // final LatLng FindLoc = new LatLng(13.281169, 100.936234);
        Log.d("park:restaurantList: ", "" + restaurantList.size());
        for (int i = 0; i < restaurantList.size(); i++) {
            LatLng FindLoc = new LatLng(restaurantList.get(i).getLatitude(), restaurantList.get(i).getLongitude());
            //SetmLocToLct(FindLoc,i);
            // requestDirection(myloc, findLoc);
        }
       /* mAdapter = new CardRestaurantSearchAdapter(RestaurantSearchActivity.this, restaurantList);
        recyclerRes.setAdapter(mAdapter);*/
    }

    private void SetmLocToLct(final LatLng findLoc, final int i) {
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng myloc = new LatLng(location.getLatitude(), location.getLongitude());
                //  mMarker = mMap.addMarker(new MarkerOptions().position(loc));
                if (mMap != null) {
                    requestDirection(myloc, findLoc);
     //               restaurantList.get(i).setDistance(Dir);
           //         Log.d( "onMyLocationChange: ",Dir);

                } else {
                    requestDirection(myloc, findLoc);
         //           restaurantList.get(i).setDistance(Dir);
             //       Log.d( "onMyLocationChange: ",Dir);
                }

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
