package com.buu.se.searchbangsaen.add_categories.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.buu.se.searchbangsaen.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddLatLngResFragment extends Fragment implements OnMapReadyCallback {


    @BindView(R.id.ivBack) ImageView ivBack;
    private MapView mapView;
    private GoogleMap googleMap;
    private LatLng selectLatLng;
    private Button btnSubmitAddmap;
    private onAddMapSuccessClickNextListener mCallBack;

    public interface onAddMapSuccessClickNextListener {
        void onSuccessToAddMapClick(LatLng selectLatLng);

        void onLatLngBackPress();
    }


    public AddLatLngResFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBack = (onAddMapSuccessClickNextListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_lat_lng, container, false);
        selectLatLng = null;

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.fm_map);
        btnSubmitAddmap = (Button) view.findViewById(R.id.btn_submit_addmap);
        btnSubmitAddmap.setVisibility(View.GONE);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        btnSubmitAddmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onSuccessToAddMapClick(selectLatLng);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onLatLngBackPress();
            }
        });

    }

    public static Fragment newInstance() {
        return new AddLatLngResFragment();
    }


    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setMyLocationEnabled(true);

        LatLng mylatlng = new LatLng(13.284791000813307, 100.92460948973894);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mylatlng, 15.0f));


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions().position(latLng));
                selectLatLng = latLng;
                btnSubmitAddmap.setVisibility(View.VISIBLE);
            }
        });
    }
}
