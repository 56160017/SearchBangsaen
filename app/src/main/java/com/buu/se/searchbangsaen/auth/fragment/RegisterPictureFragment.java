package com.buu.se.searchbangsaen.auth.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;

import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterPictureFragment extends Fragment {


    //    private int PICK_IMAGE_REQUEST = 1;
    @BindView(R.id.btn_create) Button btnCreate;
    @BindView(R.id.txt_level1) TextView txtLevel1;
    @BindView(R.id.iv_profile_add) CircleImageView ivProfileAdd;
    @BindView(R.id.imv_back) ImageView imvBack;
    @BindView(R.id.ll_add_pic) LinearLayout llAddPic;
    @BindView(R.id.ll_del_pic) LinearLayout llDelPic;

    // @BindView(R.id.add_img) ImageView addImg;

    private onClickCreateDataRegisterListener mCallBack;
    private MediaStore.Images.Media contentResolver;
    public static final int REQUEST_GALLERY = 1;
    private boolean next;
    private Bitmap bitmap;
    private Uri uri;

    public MediaStore.Images.Media getContentResolver() {
        return contentResolver;
    }

    public void setContentResolver(MediaStore.Images.Media contentResolver) {
        this.contentResolver = contentResolver;
    }

    public interface onClickCreateDataRegisterListener {
        void onSuccessToCreateDataClick(Uri picUri);
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
        next = false;

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivProfileAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent
                        , "Select Picture"), REQUEST_GALLERY);
            }
        });
        llAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent
                        , "Select Picture"), REQUEST_GALLERY);
            }
        });
        llDelPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivProfileAdd.setImageDrawable(getResources().getDrawable(R.drawable.user_profile));
                next = false;
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (next) {
                    mCallBack.onSuccessToCreateDataClick(uri);
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                ivProfileAdd.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            next = true;
        }
    }

    public static Fragment newInstance() {
        return new RegisterPictureFragment();
    }

}
