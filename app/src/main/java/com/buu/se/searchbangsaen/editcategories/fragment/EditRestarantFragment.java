package com.buu.se.searchbangsaen.editcategories.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.dao.AddRestaurantDao;
import com.buu.se.searchbangsaen.add_categories.fragment.AddLatLngResFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditRestarantFragment extends Fragment {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_time_open)
    EditText etTimeOpen;
    @BindView(R.id.et_time_close)
    EditText etTimeClose;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.btn_map)
    Button btnMap;
    private AddRestaurantDao addRestaurantDao;
    private EditMapResFragment editMapResFragment;

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
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTel.setText("1111");
                EditMapResFragment myFragment = new EditMapResFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.content_frame, myFragment);
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etName.setText(addRestaurantDao.getNameRestaurant());
        etTimeOpen.setText(addRestaurantDao.getResOpen());
        etTimeClose.setText(addRestaurantDao.getResClose());
        etTel.setText(addRestaurantDao.getResPhone());
        etAddress.setText(addRestaurantDao.getResAddress());
    }


}
