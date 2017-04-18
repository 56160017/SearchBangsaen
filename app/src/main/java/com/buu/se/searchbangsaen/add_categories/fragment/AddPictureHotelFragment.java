package com.buu.se.searchbangsaen.add_categories.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.buu.se.searchbangsaen.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPictureHotelFragment extends Fragment {

    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.btn_submit) Button btnSubmit;
    private onAddpictureSuccessClickNextListener mCallBack;

    public interface onAddpictureSuccessClickNextListener {
        void onSuccessToAddPictureClick();
        void onBackClick();
    }


    public AddPictureHotelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBack = (onAddpictureSuccessClickNextListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_picture_hotel, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSubmit.setOnClickListener(OnbtnSubmitClickListener);
        ivBack.setOnClickListener(OnbtnBackClickListener);
    }

    private View.OnClickListener OnbtnSubmitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCallBack.onSuccessToAddPictureClick();
        }
    };
    private View.OnClickListener OnbtnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCallBack.onBackClick();
        }
    };


    public static Fragment newInstance() {
        return new AddPictureHotelFragment();
    }

}
