package com.buu.se.searchbangsaen.searchcategories;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.buu.se.searchbangsaen.R;

import butterknife.ButterKnife;


public class DetailRestaurantFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_detail_restaurant, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    public static DetailRestaurantFragment newInstance() {
        return new DetailRestaurantFragment();
    }
}
