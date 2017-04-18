package com.buu.se.searchbangsaen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

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
import com.vistrav.ask.Ask;
import com.vistrav.ask.annotations.AskDenied;
import com.vistrav.ask.annotations.AskGranted;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("unused")
public class MapDirectionActivity extends FragmentActivity implements OnMapReadyCallback, DirectionCallback {


    @BindView(R.id.tv_direction) TextView tvDirection;
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

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

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
        Ask.on(this)
                .forPermissions(Manifest.permission.ACCESS_COARSE_LOCATION
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_FINE_LOCATION)
                .withRationales("Location permission need for map to work properly",
                        "In order to save file you will need to grant storage permission") //optional
                .go();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        serverkey = getResources().getString(R.string.google_maps_key);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

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
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
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

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        if (direction.isOK()) {
            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
            if (polyline == null) {
                polyline = mMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, getResources().getColor(R.color.colorPrimary)));
                Log.d("onDirectionSuccess: ", " " + direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());
                tvDirection.setText("ระยะทาง: " +direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());
            } else {
                polyline.remove();
                polyline = mMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, getResources().getColor(R.color.colorPrimary)));
                tvDirection.setText("ระยะทาง: " +direction.getRouteList().get(0).getLegList().get(0).getDistance().getText());
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
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
//            CameraPosition cameraPosition = new CameraPosition.Builder()
//                    .target(new LatLng(location.getLatitude(), location.getLongitude()))
//                    .zoom(14)
//                    .build();
//
//            if (mMap != null) {
//                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//            }
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

    @AskGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void fileAccessGranted() {
        Log.i(TAG, "FILE  GRANTED");
    }

    //optional
    @AskDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void fileAccessDenied() {
        Log.i(TAG, "FILE  DENiED");
        mMap.setMyLocationEnabled(true);
    }

    //optional
    @AskGranted(Manifest.permission.ACCESS_COARSE_LOCATION)
    public void mapAccessGranted() {
        Log.i(TAG, "MAP GRANTED");
    }

    //optional
    @AskDenied(Manifest.permission.ACCESS_COARSE_LOCATION)
    public void mapAccessDenied() {
        Log.i(TAG, "MAP DENIED");
    }

    //optional
    @AskGranted(Manifest.permission.ACCESS_COARSE_LOCATION)
    public void myLocationGranted() {
        //  mMap.setMyLocationEnabled(true);
        Log.i(TAG, "MAP GRANTED");
    }

    //optional
    @AskDenied(Manifest.permission.ACCESS_COARSE_LOCATION)
    public void myLocationDenied() {
        Log.i(TAG, "MAP DENIED");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
