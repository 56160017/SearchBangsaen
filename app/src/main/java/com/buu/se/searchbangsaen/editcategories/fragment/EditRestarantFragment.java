package com.buu.se.searchbangsaen.editcategories.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.dao.AddRestaurantDao;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditRestarantFragment extends Fragment {
    @BindView(R.id.editText) EditText editText;
    private AddRestaurantDao addRestaurantDao;

    public EditRestarantFragment() {
    }

    public EditRestarantFragment(AddRestaurantDao addRestaurantDao) {
        // Required empty public constructor
        this.addRestaurantDao = addRestaurantDao;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_restarant, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText.setText(addRestaurantDao.getNameRestaurant());
    }
}
