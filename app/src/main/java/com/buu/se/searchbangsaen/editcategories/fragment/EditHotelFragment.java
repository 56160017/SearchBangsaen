package com.buu.se.searchbangsaen.editcategories.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.buu.se.searchbangsaen.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditHotelFragment extends Fragment {


    public EditHotelFragment() {
        // Required empty public constructor
    }

    public static EditHotelFragment newInstance() {
        EditHotelFragment editHotelFragment = new EditHotelFragment();
        return editHotelFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_hotel, container, false);
    }

}
