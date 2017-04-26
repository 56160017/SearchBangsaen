package com.buu.se.searchbangsaen.auth.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterDetailFragment extends Fragment {

    @BindView(R.id.txt_level1) TextView txtLevel1;
    @BindView(R.id.imv_back) ImageView imvBack;
    @BindView(R.id.rl_appbar) RelativeLayout rlAppbar;
    @BindView(R.id.toolbar) AppBarLayout toolbar;
    @BindView(R.id.et_name) EditText etName;
    @BindView(R.id.et_sname) EditText etSname;
    @BindView(R.id.et_nb_phone) EditText etNbPhone;
    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.btn_submit) Button btnSubmit;
    private onClickToPictureRegisterListener mCallBack;

    public interface onClickToPictureRegisterListener {
        void onSuccessToRegisterPictureClick(String name, String sname, String phone, String email);
        void onBackDetailClick();
    }


    public RegisterDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBack = (onClickToPictureRegisterListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imvBackOnClickListener();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().trim().matches("")) {
                    etName.setError("Username is invalid");
                    return;
                }
                if(etSname.getText().toString().trim().matches("")){
                    etName.setError("Surname is invalid");
                    return;
                }
                if(etNbPhone.getText().toString().trim().matches("")){
                    etNbPhone.setError("Number Phone is invalid");
                    return;
                }
                if(!isValidEmail(etEmail.getText().toString())){
                    etEmail.setError("Email is invalid");
                    return;
                }


                mCallBack.onSuccessToRegisterPictureClick(etName.getText().toString(), etSname.getText().toString()
                        , etNbPhone.getText().toString(), etEmail.getText().toString());
            }
        });
    }

    private void imvBackOnClickListener() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onBackDetailClick();
            }
        });
    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 5) {
            return true;
        }
        return false;
    }
    public static Fragment newInstance() {
        return new RegisterDetailFragment();
    }
}
