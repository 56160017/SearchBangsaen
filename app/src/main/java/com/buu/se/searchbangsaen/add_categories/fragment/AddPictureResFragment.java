package com.buu.se.searchbangsaen.add_categories.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.adapter.AddImageAdapter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPictureResFragment extends Fragment {

    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.btn_submit) Button btnSubmit;
    @BindView(R.id.gv_image) GridView gvImage;
    @BindView(R.id.btn_add_image) Button btnAddImage;
    public static final int REQUEST_GALLERY = 1;
    @BindView(R.id.rl_error) RelativeLayout rlError;

    private onAddpictureSuccessClickNextListener mCallBack;
    private Uri uri;
    private Bitmap bitmap;
    private List<Uri> mUri;
    private Context mContext;
    private AddImageAdapter ivAdapter;

    public interface onAddpictureSuccessClickNextListener {
        void onSuccessToAddPictureClick(List<Uri> mUri);

        void onBackClick();
    }


    public AddPictureResFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_picture_res, container, false);
        ButterKnife.bind(this, view);
        mUri = new ArrayList<>();
        ivAdapter = new AddImageAdapter(mContext);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAddImage.setOnClickListener(OnbtnAddImageClickListener);
        btnSubmit.setOnClickListener(OnbtnSubmitClickListener);
        ivBack.setOnClickListener(OnbtnBackClickListener);
        gvImage.setAdapter(ivAdapter);


        // gvBenefit.setAdapter(new BenefitsAdapter(this, restaurantListDao.getBenefitsDao()));

        gvImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d( "onItemClick: ",""+position);
            }
        });
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
        rlError.setVisibility(View.GONE);
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                //ivProfileAdd.setImageBitmap(bitmap);
                gvImage.setNumColumns(3);
                ivAdapter.addUri(uri);
                gvImage.setAdapter(ivAdapter);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Fragment newInstance() {
        return new AddPictureResFragment();
    }

}
