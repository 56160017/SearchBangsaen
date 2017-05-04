package com.buu.se.searchbangsaen.hotel_categories.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.constant.Unit;
import com.akexorcist.googledirection.model.Direction;
import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.auth.activity.AuthActivity;
import com.buu.se.searchbangsaen.hotel_categories.adapter.CardHotelAdapter;
import com.buu.se.searchbangsaen.hotel_categories.dao.BenefitsHotelDao;
import com.buu.se.searchbangsaen.hotel_categories.dao.HotelDao;
import com.buu.se.searchbangsaen.hotel_categories.dao.RelaxsDao;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
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

public class HotelActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {


    @BindView(R.id.tvNameTitle) TextView tvNameTitle;
    @BindView(R.id.recycler_hotel) RecyclerView recyclerHotel;
    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.tvTitle) CardView tvTitle;
    @BindView(R.id.ivAdd) ImageView ivAdd;

    //map
    private String serverkey;
    private GoogleApiClient googleApiClient;


    //permission
    private static final int REQUEST_LOCATION_PER = 103;

    private RecyclerView.LayoutManager mLayoutManager;
    private CardHotelAdapter mAdapter;

    private List<HotelDao> HotelList;
    private BenefitsHotelDao benefitdaos;
    private RelaxsDao relaxDao;
    private LatLng myLoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        ButterKnife.bind(this);
        ivBack.setOnClickListener(OnClickBackListener);
        ivAdd.setOnClickListener(OnClickAddListener);
        setBgPage();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  //เช็ค permission
            checkLocationPermission();
        }

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        recyclerHotel.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerHotel.setLayoutManager(mLayoutManager);
        initRestaurantData();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void setBgPage() {
        tvTitle.setBackgroundColor(getResources().getColor(R.color.title_bar_hotel));
        tvNameTitle.setText("โรงแรม");
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
                    // dataSnapshot is the "issue" node with all children with id 0

                    for (DataSnapshot uuid : dataSnapshot.getChildren()) {
                        Log.d("onDataChangeID: ",""+uuid.getKey());
                        benefitdaos = new BenefitsHotelDao();
                        relaxDao =   new RelaxsDao();
                        HotelDao a = new HotelDao();
                        List<Uri> mUri = new ArrayList<Uri>();
                        for (DataSnapshot getuuid : uuid.getChildren()){
                            a.setDistance("กำลังคำนวณ");
                            switch (getuuid.getKey()){
                                case "name":
                                    a.setName("" + getuuid.getValue());
                                    break;
                                case "phone": a.setPhone("" + getuuid.getValue());
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
                                case "benefits" :
                                    for (DataSnapshot getbenefits : getuuid.getChildren()) {
                                    switch (getbenefits.getKey()) {
                                        case "parking":
                                            Log.d( "onDataChange2: ",""+ getbenefits.getValue());
                                            benefitdaos.setChk_parking(stringToBool("" + getbenefits.getValue()));
                                            break;
                                        case "shop":
                                            benefitdaos.setChk_shop(stringToBool("" + getbenefits.getValue()));
                                            break;
                                        case "internat":
                                            benefitdaos.setChk_internat(stringToBool("" + getbenefits.getValue()));
                                            break;
                                        case "dry":
                                            benefitdaos.setChk_dry(stringToBool("" + getbenefits.getValue()));
                                            break;
                                        case "taxi":
                                            benefitdaos.setChk_taxi(stringToBool("" + getbenefits.getValue()));
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                    a.setBenefitHotelDao(benefitdaos);
                                    break;
                                case "relaxs" :
                                    for (DataSnapshot getrelaxs : getuuid.getChildren()) {
                                        switch (getrelaxs.getKey()) {
                                            case "fitness":
                                                relaxDao.setFitness(stringToBool("" + getrelaxs.getValue()));
                                                break;
                                            case "golf":
                                                relaxDao.setGolf(stringToBool("" + getrelaxs.getValue()));
                                                break;
                                            case "playground":
                                                relaxDao.setPlayground(stringToBool("" + getrelaxs.getValue()));
                                                break;
                                            case "swim":
                                                relaxDao.setSwim(stringToBool("" + getrelaxs.getValue()));
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    a.setRelaxDao(relaxDao);
                                    break;
                                case "pic":
                                    for (DataSnapshot getpics : getuuid.getChildren()) {
                                        mUri.add(Uri.parse("" + getpics.getValue()));
                                        Log.d("getpics//" + getpics.getKey() + ":", " " + getpics.getValue());
                                    }
                                    //    a.setTypeResDao(typeResDaos);
                                    a.setmUri(mUri);
                                    break;
                                default: break;
                            }
                            Log.d("onDataChange: ",""+getuuid.getValue());
                        }
                        //String timeStamp = new SimpleDateFormat("HH.mm.ss").format(Calendar.getInstance().getTime())
                        Log.d("onMyLocationChange1", "test");

                        HotelList.add(a);

                    }

                    mAdapter = new CardHotelAdapter(HotelActivity.this, HotelList);
                    recyclerHotel.setAdapter(mAdapter);

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
        throw new IllegalArgumentException(s + " is not a bool. Only 1 and 0 are.");
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
                    .setInterval(0)
                    .setNumUpdates(1);

            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
            Log.d("onConnected: ", "ture");
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
    public void onLocationChanged(Location location) {

        Double latitude = (location.getLatitude());
        Double longitude = (location.getLongitude());
        Log.d("onMyLocationChange1", "Latitude: " + latitude + ", Longitude: " + longitude);
        myLoc = new LatLng(latitude, longitude);
        LatLng FindLoc;
        for (int i = 0; i < HotelList.size(); i++) {
            FindLoc = new LatLng(HotelList.get(i).getLatitude(), HotelList.get(i).getLongitude());
            requestDirection(myLoc, FindLoc,i);
            Log.d("park:DirectionSuccess23: ", HotelList.get(i).getName());

        }

        mAdapter = new CardHotelAdapter(HotelActivity.this, HotelList);
        recyclerHotel.setAdapter(mAdapter);

    }

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
                        HotelList.get(i).setDistance(""+ direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());

                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {

                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient != null && googleApiClient.isConnected()) {
            // Disconnect Google API Client if available and connected
            googleApiClient.disconnect();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
