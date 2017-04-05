package com.buu.se.searchbangsaen.auth.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.buu.se.searchbangsaen.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterDetailFragment extends Fragment {

    @BindView(R.id.btn_submit) Button btnSubmit;
    @BindView(R.id.et_name) EditText etName;
    @BindView(R.id.et_sname) EditText etSname;
    @BindView(R.id.et_nb_phone) EditText etNbPhone;
    @BindView(R.id.et_email) EditText etEmail;
    private onClickToPictureRegisterListener mCallBack;

    public interface onClickToPictureRegisterListener {
        void onSuccessToRegisterPictureClick(String name, String sname, String phone, String email);
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
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onSuccessToRegisterPictureClick(etName.getText().toString(),etSname.getText().toString()
                                                         ,etNbPhone.getText().toString(),etEmail.getText().toString());
            }
        });
    }

    public static Fragment newInstance() {
        return new RegisterDetailFragment();
    }
}