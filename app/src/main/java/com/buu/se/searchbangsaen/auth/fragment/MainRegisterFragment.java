package com.buu.se.searchbangsaen.auth.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.auth.adapter.RegisterAdapter;
import com.buu.se.searchbangsaen.utils.SearchNonSwipeableViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainRegisterFragment extends Fragment  {


    @BindView(R.id.vp_identify_cus) SearchNonSwipeableViewPager vpIdentifyCus;
    private RegisterAdapter registerAdapter;

    public MainRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        registerAdapter = new RegisterAdapter(getChildFragmentManager());
        vpIdentifyCus.setAdapter(registerAdapter);
    }
    public static Fragment newInstance() {
        return new MainRegisterFragment();
    }


    public void changeToRegisterDetailClick() {
        vpIdentifyCus.setCurrentItem(1,true);
    }
    public void changeToRegisterPictureClick() {
        vpIdentifyCus.setCurrentItem(2,true);
    }

    public void backToRegisterDetailClick() {
        vpIdentifyCus.setCurrentItem(0,true);
    }
    public void onBackStepFragment() {
        if(vpIdentifyCus.getCurrentItem() == 1) {
            vpIdentifyCus.setCurrentItem(0, true);
        }else if(vpIdentifyCus.getCurrentItem() == 2){
            vpIdentifyCus.setCurrentItem(1, true);
        }else{
            vpIdentifyCus.setCurrentItem(0, true);
        }
    }
    public Fragment getViewpagerRegisterFragment(){
        return registerAdapter.getRegisteredFragment(vpIdentifyCus.getCurrentItem());
    }
}
