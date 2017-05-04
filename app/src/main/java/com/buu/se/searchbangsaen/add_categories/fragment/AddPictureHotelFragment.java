package com.buu.se.searchbangsaen.add_categories.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.adapter.AddImageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPictureHotelFragment extends Fragment {

    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.btn_submit) Button btnSubmit;
    @BindView(R.id.btn_add_image) Button btnAddImage;
    @BindView(R.id.gv_image) GridView gvImage;
    public static final int REQUEST_GALLERY = 1;
    @BindView(R.id.rl_error) RelativeLayout rlError;

    private onAddpictureSuccessClickNextListener mCallBack;
    private List<Uri> mUri;
    private Context mContext;
    private AddImageAdapter ivAdapter;
    private Uri uri;

    public interface onAddpictureSuccessClickNextListener {
        void onSuccessToAddPictureClick(List<Uri> mUri);

        void onBackClick();
    }


    public AddPictureHotelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mCallBack = (onAddpictureSuccessClickNextListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_picture_hotel, container, false);
        ButterKnife.bind(this, view);
        mUri = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAddImage.setOnClickListener(OnbtnAddImageClickListener);
        btnSubmit.setOnClickListener(OnbtnSubmitClickListener);
        ivBack.setOnClickListener(OnbtnBackClickListener);

        ivAdapter = new AddImageAdapter(mContext);
    }

    private View.OnClickListener OnbtnAddImageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent
                    , "Select Picture"), REQUEST_GALLERY);
        }
    };
    private View.OnClickListener OnbtnSubmitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mUri = ivAdapter.getAllUri();
            if (mUri.size() > 0) {
                mCallBack.onSuccessToAddPictureClick(mUri);
            } else {
                rlError.setVisibility(View.VISIBLE);
            }
        }
    };
    private View.OnClickListener OnbtnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCallBack.onBackClick();
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            uri = data.getData();
            // bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
            //ivProfileAdd.setImageBitmap(bitmap);
            gvImage.setNumColumns(3);
            ivAdapter.addUri(uri);
            gvImage.setAdapter(ivAdapter);

        }
    }

    public static Fragment newInstance() {
        return new AddPictureHotelFragment();
    }

}
