package com.buu.se.searchbangsaen.auth.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.buu.se.searchbangsaen.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterPictureFragment extends Fragment {


    @BindView(R.id.btn_create) Button btnCreate;

    private onClickCreateDataRegisterListener mCallBack;

    public interface onClickCreateDataRegisterListener {
        void onSuccessToCreateDataClick();
    }

    public RegisterPictureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBack= (onClickCreateDataRegisterListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_picture, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onSuccessToCreateDataClick();
            }
        });
    }

    public static Fragment newInstance() {
        return new RegisterPictureFragment();
    }

}
