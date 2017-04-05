package com.buu.se.searchbangsaen.auth.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterPictureFragment extends Fragment {



//    private int PICK_IMAGE_REQUEST = 1;
    @BindView(R.id.btn_create) Button btnCreate;
    @BindView(R.id.txt_level1) TextView txtLevel1;
    @BindView(R.id.add_img) ImageView addImg;

    private onClickCreateDataRegisterListener mCallBack;
    private MediaStore.Images.Media contentResolver;

    public MediaStore.Images.Media getContentResolver() {
        return contentResolver;
    }

    public void setContentResolver(MediaStore.Images.Media contentResolver) {
        this.contentResolver = contentResolver;
    }

    public interface onClickCreateDataRegisterListener {
        void onSuccessToCreateDataClick();
    }

    public RegisterPictureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBack = (onClickCreateDataRegisterListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_picture, container, false);
        ButterKnife.bind(this, view);
//        addImgOnClickListener();
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
