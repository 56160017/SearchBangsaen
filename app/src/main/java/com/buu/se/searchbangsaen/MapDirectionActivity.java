package com.buu.se.searchbangsaen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransitMode;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.constant.Unit;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("unused")
public class MapDirectionActivity extends FragmentActivity implements OnMapReadyCallback, DirectionCallback {


    @BindView(R.id.tv_direction)
    TextView tvDirection;

    private static final int REQUEST_LOCATION_PER = 103;
    private GoogleMap mMap;
    private String serverkey;
    private Marker mMarker;
    private Polyline polyline;

    private LocationManager locationManager;
    //google use location dialog
    protected GoogleApiClient mGoogleApiClient;
    protected LocationRequest locationRequest;

    private LatLng mymap;
    private double myLatitude = 0;
    private double myLongitude = 0;

    private String locationName;
    private String locationLoc;
    private double locationLatitude = 0;
    private double locationLongitude = 0;



    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_direction);
        ButterKnife.bind(this);
        Intent in = getIntent();
        locationLatitude = in.getDoubleExtra("lat", 0.00);
        locationLongitude = in.getDoubleExtra("lng", 0.00);
        locationName = in.getStringExtra("name");
        locationLoc = in.getStringExtra("loc");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        serverkey = getResources().getString(R.string.google_maps_key);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  //เช็ค permission
            checkLocationPermission();
        } else {
            mMap.setMyLocationEnabled(true);
        }
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.


        // Add a marker in Sydney and move the camera
        //13.281169, 100.936234
        final LatLng FindLoc = new LatLng(locationLatitude, locationLongitude);
        //  final LatLng FindLoc = new LatLng(13.290419, 100.926324);
        //setMylocation();
        mMap.addMarker(new MarkerOptions()
                        .position(FindLoc)
                        .title(locationName)
                        .snippet(locationLoc)
                //  .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_circle_food_small))
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FindLoc, 15.0f));


        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
                Log.d("onMyLocationChange2: ", "getLatitude:" + loc.latitude + "  getLongitude:" + loc.longitude);
                //  mMarker = mMap.addMarker(new MarkerOptions().position(loc));
                if (mMap != null) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 15.0f));
                    requestDirection(loc, FindLoc);
                }
            }
        });
    }

    private void requestDirection(LatLng current, LatLng destination) {
        GoogleDirection.withServerKey(serverkey)
                .from(current)
                .to(destination)
                .transportMode(TransportMode.DRIVING)
                .transitMode(TransitMode.RAIL)
                .unit(Unit.METRIC)
                .execute(MapDirectionActivity.this);

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

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION_PER: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                     /*   if(mGoogleApiClient == null){
                            build

                        }*/
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Permission false", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        if (direction.isOK()) {
            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
            if (polyline == null) {
                polyline = mMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, getResources().getColor(R.color.colorPrimary)));
                Log.d("onDirectionSuccess: ", " " + direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());
                tvDirection.setText("ระยะทาง: " + direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());
            } else {
                polyline.remove();
                polyline = mMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, getResources().getColor(R.color.colorPrimary)));
                tvDirection.setText("ระยะทาง: " + direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());
            }
        }
    }

    private void setMylocation() {
        if (mMap != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);

                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_FINE);
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                String bestProvider = locationManager.getBestProvider(criteria, true);

//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                        !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    mGoogleApiClient = new GoogleApiClient.Builder(this)
                            .addApi(LocationServices.API)
                            .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
                            .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this).build();
                    mGoogleApiClient.connect();

                    locationRequest = LocationRequest.create();
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    locationRequest.setInterval(30 * 1000);
                    locationRequest.setFastestInterval(2 * 1000);
                } else {
                    locationManager.requestLocationUpdates(bestProvider, 0, 0, locationListener);

                    Location location = getLastKnownLocation();


                    if (location != null) {

                        myLatitude = location.getLatitude();
                        myLongitude = location.getLongitude();
                    }


                    mymap = new LatLng(myLatitude, myLongitude);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mymap, 14));
                }
            }

        }
    }

    private Location getLastKnownLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            }
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            getMyLocation();
        }

        private void getMyLocation() {
            Location location = getLastKnownLocation();


            if (location != null) {

                myLatitude = location.getLatitude();
                myLongitude = location.getLongitude();
                Log.v(TAG, "myLatitude : " + myLatitude);
                Log.v(TAG, "myLongitude : " + myLongitude);
            }

            mymap = new LatLng(myLatitude, myLongitude);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mymap, 14);
            mMap.animateCamera(cameraUpdate);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    public void onDirectionFailure(Throwable t) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
