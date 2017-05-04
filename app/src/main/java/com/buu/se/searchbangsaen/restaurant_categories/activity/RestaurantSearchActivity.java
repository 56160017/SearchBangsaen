package com.buu.se.searchbangsaen.restaurant_categories.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.constant.Unit;
import com.akexorcist.googledirection.model.Direction;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.dao.TypeResDao;
import com.buu.se.searchbangsaen.auth.activity.AuthActivity;
import com.buu.se.searchbangsaen.restaurant_categories.adapter.CardRestaurantSearchAdapter;
import com.buu.se.searchbangsaen.restaurant_categories.dao.BenefitsDao;
import com.buu.se.searchbangsaen.restaurant_categories.dao.DatesDao;
import com.buu.se.searchbangsaen.restaurant_categories.dao.RestaurantDao;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RestaurantSearchActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    @BindView(R.id.recycler_res) RecyclerView recyclerRes;
    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.ivAdd) ImageView ivAdd;
    @BindView(R.id.btn_connecting) Button btnConnecting;
    @BindView(R.id.fl_connecting) FrameLayout flConnecting;
    @BindView(R.id.fl_map) FrameLayout flMap;

    private RecyclerView.LayoutManager mLayoutManager;
    private CardRestaurantSearchAdapter mAdapter;

    //map
    private String serverkey;
    private GoogleApiClient googleApiClient;


    //permission
    private static final int REQUEST_LOCATION_PER = 103;


    private List<RestaurantDao> restaurantList;

    //FirebaseAuth
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);
         ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  //เช็ค permission
            checkLocationPermission();
        }
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

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

        ivBack.setOnClickListener(OnClickBackListener);
        ivAdd.setOnClickListener(OnClickAddBackListener);
        btnConnecting.setOnClickListener(OnClickReconnectBackListener);
        serverkey = getResources().getString(R.string.google_maps_key);
            checkInternet();





    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PER);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PER);
            }
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION_PER: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                     /*   if(mGoogleApiClient == null){
                            build

                        }*/

                    }
                } else {
                    Toast.makeText(this, "Permission false", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //checkInternet();
        Log.d("test", "onResume");
    }

    private void checkInternet() {
        if (isNetworkConnected()) {
            flConnecting.setVisibility(View.GONE);

            initRestaurantData();
        } else {
            flConnecting.setVisibility(View.VISIBLE);
            final Dialog dialog = new Dialog(RestaurantSearchActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_disconnect);
            dialog.setCancelable(true);

            Button button1 = (Button) dialog.findViewById(R.id.button1);
            button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext()
                            , "Close dialog", Toast.LENGTH_SHORT);
                    dialog.cancel();
                }
            });
            TextView textView2 = (TextView) dialog.findViewById(R.id.textView2);
            textView2.setText("Connection to failed");
            dialog.show();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void initRestaurantData() {
        restaurantList = new ArrayList<>();
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        Query query = mRootRef.child("restaurant");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    int i = 0;
                    for (DataSnapshot uuid : dataSnapshot.getChildren()) {
                        Log.d("onDataChangeID: ", "" + uuid.getKey());
                        RestaurantDao a = new RestaurantDao();
                        BenefitsDao benefitdaos = new BenefitsDao();
                        TypeResDao typeResDaos = new TypeResDao();
                        DatesDao dateDaos = new DatesDao();
                        List<Uri> mUri = new ArrayList<Uri>();
                        i++;
                        for (DataSnapshot getuuid : uuid.getChildren()) {

                            a.setId(i);
                            a.setDistance("กำลังคำนวณ");
                            switch (getuuid.getKey()) {
                                case "name":
                                    a.setName("" + getuuid.getValue());
                                    break;
                                case "phone":
                                    a.setContact("" + getuuid.getValue());
                                    break;
                                case "time-open":
                                    a.setOpen("" + getuuid.getValue());
                                    break;
                                case "time-close":
                                    a.setClose("" + getuuid.getValue());
                                    break;
                                case "dates-open":
                                    a.setDay("" + getuuid.getValue());
                                    break;
                                case "latitude":
                                    a.setLatitude(Double.parseDouble("" + getuuid.getValue()));
                                    break;
                                case "longitude":
                                    a.setLongitude(Double.parseDouble("" + getuuid.getValue()));
                                    break;
                                case "address":
                                    a.setLocation("" + getuuid.getValue());
                                    break;
                                case "benefits":
                                    for (DataSnapshot getbenefits : getuuid.getChildren()) {
                                        switch (getbenefits.getKey()) {
                                            case "parking":
                                                benefitdaos.setParking(stringToBool("" + getbenefits.getValue()));
                                                break;
                                            case "alcohol":
                                                benefitdaos.setAlcohol(stringToBool("" + getbenefits.getValue()));
                                                break;
                                            case "creditcards":
                                                benefitdaos.setCreditCards(stringToBool("" + getbenefits.getValue()));
                                                break;
                                            case "livemusic":
                                                benefitdaos.setLiveMusic(stringToBool("" + getbenefits.getValue()));
                                                break;
                                            case "reservation":
                                                benefitdaos.setReservation(stringToBool("" + getbenefits.getValue()));
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    a.setBenefitsDao(benefitdaos);
                                    break;
                                case "dates":
                                    for (DataSnapshot getdates : getuuid.getChildren()) {
                                        switch (getdates.getKey()) {
                                            case "sunday":
                                                dateDaos.setSun(stringToBool("" + getdates.getValue()));
                                                break;
                                            case "monday":
                                                dateDaos.setMonday(stringToBool("" + getdates.getValue()));
                                                break;
                                            case "tuesday":
                                                dateDaos.setTuesday(stringToBool("" + getdates.getValue()));
                                                break;
                                            case "wednesday":
                                                dateDaos.setWed(stringToBool("" + getdates.getValue()));
                                                break;
                                            case "thursday":
                                                dateDaos.setThursday(stringToBool("" + getdates.getValue()));
                                                break;
                                            case "friday":
                                                dateDaos.setFriday(stringToBool("" + getdates.getValue()));
                                                break;
                                            case "saturday":
                                                dateDaos.setSaturday(stringToBool("" + getdates.getValue()));
                                                break;
                                            default:
                                                break;
                                        }
                                        a.setDatesDao(dateDaos);//ยังไม่ได้สร้าง
                                    }
                                    break;
                                case "typefoods":
                                    for (DataSnapshot gettypefoods : getuuid.getChildren()) {
                                        switch (gettypefoods.getKey()) {
                                            case "buffet":
                                                typeResDaos.setChkBuffet(stringToBool("" + gettypefoods.getValue()));
                                                Log.d("test-" + gettypefoods.getKey(), "" + typeResDaos.isChkBuffet());
                                                break;
                                            case "esan-food":
                                                typeResDaos.setChkEsanfood(stringToBool("" + gettypefoods.getValue()));
                                                Log.d("test-" + gettypefoods.getKey(), "" + typeResDaos.isChkEsanfood());
                                                break;
                                            case "sea-food":
                                                typeResDaos.setChkSea(stringToBool("" + gettypefoods.getValue()));
                                                Log.d("pa" + gettypefoods.getKey(), "" + gettypefoods.getValue());
                                                break;
                                            case "single-food":
                                                typeResDaos.setChkSingleFood(stringToBool("" + gettypefoods.getValue()));
                                                Log.d("pa" + gettypefoods.getKey(), "" + gettypefoods.getValue());
                                                break;
                                            case "thai-food":
                                                typeResDaos.setChkThai(stringToBool("" + gettypefoods.getValue()));
                                                Log.d("pa" + gettypefoods.getKey(), "" + gettypefoods.getValue());
                                                break;
                                            case "wild-food":
                                                typeResDaos.setChkWildfood(stringToBool("" + gettypefoods.getValue()));
                                                Log.d("pa" + gettypefoods.getKey(), "" + gettypefoods.getValue());
                                                break;
                                            default:
                                                break;
                                        }

                                    }
                                    a.setTypeResDao(typeResDaos);
                                    break;
                                //////////////////
                                case "pic":
                                    for (DataSnapshot getpics : getuuid.getChildren()) {
                                        mUri.add(Uri.parse("" + getpics.getValue()));
                                        Log.d("getpics//" + getpics.getKey() + ":", " " + getpics.getValue());
                                    }
                                    //    a.setTypeResDao(typeResDaos);
                                    a.setmUri(mUri);
                                    break;

                                default:
                                    break;
                            }
                            Log.d("onDataChange: ", "" + getuuid.getValue());
                        }
                        //String timeStamp = new SimpleDateFormat("HH.mm.ss").format(Calendar.getInstance().getTime());

                        String timeStamp = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
                        Log.d("a.getOpen()", a.getOpen());

                        a.setStatus(isRestaurantopne(a, timeStamp));

                        restaurantList.add(a);


                    }


                    mAdapter = new CardRestaurantSearchAdapter(RestaurantSearchActivity.this, restaurantList);
                      recyclerRes.setAdapter(mAdapter);

                    //    mapFragment.getMapAsync(RestaurantSearchActivity.this);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private boolean isRestaurantopne(RestaurantDao a, String timeStamp) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String format = s.format(new Date());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateformat.parse(format);
            DateFormat dayFormate = new SimpleDateFormat("EEEE");
            String dayFromDate = dayFormate.format(date);
            Log.d("asd", "----------:: " + dayFromDate);
           /* if (dayFromDate.matches(a.getDateDao().isFriday())) {

            }*/
            if (a.getDatesDao().isSun()) {
                if (dayFromDate.matches("วันอาทิตย์")) {
                    if (Integer.parseInt(a.getOpen().substring(0, 2)) <= Integer.parseInt(timeStamp) &&
                            Integer.parseInt(a.getClose().substring(0, 2)) > Integer.parseInt(timeStamp)) {
                        Log.d("CheckDate: ", "open");
                        return true;
                    }
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean stringToBool(String s) {
        if (s.equals("true"))
            return true;
        if (s.equals("false"))
            return false;
        throw new IllegalArgumentException(s + " is not a bool. Only 1 and 0 are.");
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
    private View.OnClickListener OnClickReconnectBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            checkInternet();


        }
    };


  /*  @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        Log.d("park:DirectionSuccess: ", direction.getStatus());
        Log.d("park:DirectionSuccess: ", " " + direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());
      *//*  Dir.add(""+direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());
      *//*
        restaurantList.get(0).setDistance(""+direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Log.d("onDirectionSuccess: ", "bad");
    }
*/

    private void requestDirection(LatLng current, LatLng destination, final int i) {
        GoogleDirection.withServerKey(serverkey)
                .from(current)
                .to(destination)
                .transportMode(TransportMode.DRIVING)
                .unit(Unit.METRIC)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        Log.d("park:DirectionSuccess: ", " " + direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());
                        restaurantList.get(i).setDistance("" + direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());

                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {
                        Log.d("park:DirectionSuccess: ", "false");
                    }
                });
    }


    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

        Double latitude = (location.getLatitude());
        Double longitude = (location.getLongitude());
        Log.d("onMyLocationChange1", "Latitude: " + latitude + ", Longitude: " + longitude);
        LatLng myLoc = new LatLng(latitude, longitude);
        //  Log.d("park:restaurantList: ", "" + restaurantList.size());
        // requestDirection(myLoc,);
        // LatLng FindLoc = new LatLng(13.290419, 100.926324);
        //distance
        LatLng FindLoc;
        for (int i = 0; i < restaurantList.size(); i++) {
            FindLoc = new LatLng(restaurantList.get(i).getLatitude(), restaurantList.get(i).getLongitude());
            requestDirection(myLoc, FindLoc, i);

            Log.d("park:DirectionSuccess23: ", restaurantList.get(i).getName());

        }

        mAdapter = new CardRestaurantSearchAdapter(RestaurantSearchActivity.this, restaurantList);
        recyclerRes.setAdapter(mAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("test", "onStart");
        mAuth.addAuthStateListener(mAuthListener);
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        if (googleApiClient != null && googleApiClient.isConnected()) {
            // Disconnect Google API Client if available and connected
            googleApiClient.disconnect();
        }
        Log.d("test", "onStop");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationAvailability locationAvailability = LocationServices.FusedLocationApi.getLocationAvailability(googleApiClient);
        if (locationAvailability.isLocationAvailable()) {
            LocationRequest locationRequest = new LocationRequest()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setNumUpdates(1)
                    .setInterval(0);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
            Log.d("onConnected: ", "False");
        } else {
            // Do something when location provider not available
            Log.d("onConnected: ", "False");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
