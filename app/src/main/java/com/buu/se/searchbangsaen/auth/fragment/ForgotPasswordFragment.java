package com.buu.se.searchbangsaen.auth.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 19/4/2560.
 */

public class ForgotPasswordFragment extends Fragment {


    @BindView(R.id.txt_level1)
    TextView txtLevel1;
    @BindView(R.id.imv_back)
    ImageView imvBack;
    @BindView(R.id.rl_appbar)
    RelativeLayout rlAppbar;
    @BindView(R.id.toolbar)
    AppBarLayout toolbar;
    @BindView(R.id.txt_add_email)
    TextView txtAddEmail;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.card_view_forgot)
    CardView cardViewForgot;
    @BindView(R.id.btn_create)
    Button btnCreate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_forgot_password, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static Fragment newInstance() {
        return new ForgotPasswordFragment();
    }

}

