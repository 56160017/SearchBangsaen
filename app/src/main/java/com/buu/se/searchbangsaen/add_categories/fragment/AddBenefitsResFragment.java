package com.buu.se.searchbangsaen.add_categories.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.adapter.RecyclerAddImageAdapter;
import com.buu.se.searchbangsaen.add_categories.dao.AddImageDao;
import com.buu.se.searchbangsaen.add_categories.dao.BenefitDao;
import com.buu.se.searchbangsaen.add_categories.dao.TypeResDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddBenefitsResFragment extends Fragment implements RecyclerAddImageAdapter.onAddImageClickListener  {


    @BindView(R.id.btn_submit_final_add) Button btnSubmitFinalAdd;
    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.chkSea) CheckBox chkSea;
    @BindView(R.id.chkSingleFood) CheckBox chkSingleFood;
    @BindView(R.id.chkThai) CheckBox chkThai;
    @BindView(R.id.chkWildfood) CheckBox chkWildfood;
    @BindView(R.id.chkEsanfood) CheckBox chkEsanfood;
    @BindView(R.id.chkBuffet) CheckBox chkBuffet;
    @BindView(R.id.chkParking) CheckBox chkParking;
    @BindView(R.id.chkCreditCards) CheckBox chkCreditCards;
    @BindView(R.id.chkLiveMusic) CheckBox chkLiveMusic;
    @BindView(R.id.chkReservation) CheckBox chkReservation;
    @BindView(R.id.chkAlcohol) CheckBox chkAlcohol;


    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAddImageAdapter mAdapter;
    public static final int REQUEST_GALLERY = 1;
    private List<AddImageDao> imageList;


    private TypeResDao typeResDao;
    private BenefitDao benefitDao;
    private onAddBenefitsSuccessClickNextListener mCallBack;
    private Uri uri;
    private Context mContext;

    @Override
    public void onSuccessAddImageClick() {
        Log.d("onActivityResult: ","xxxxxxxx");
    }


    public interface onAddBenefitsSuccessClickNextListener {
        void onSuccessToAddBenefitsClick(TypeResDao typeResDao, BenefitDao benefitDao);
        void onBenefitsBackPress();
    }

    public AddBenefitsResFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mCallBack = (onAddBenefitsSuccessClickNextListener) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_benefits_res, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        typeResDao = new TypeResDao();
        benefitDao = new BenefitDao();

        btnSubmitFinalAdd.setOnClickListener(OnAddBenefitsReClick);
        ivBack.setOnClickListener(OnBackPressClick);

    }

    protected View.OnClickListener OnAddBenefitsReClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setDataToActivity();
        }

        private void setDataToActivity() {
            typeResDao.setChkSea(chkSea.isChecked());
            typeResDao.setChkSingleFood(chkSingleFood.isChecked());
            typeResDao.setChkThai(chkThai.isChecked());
            typeResDao.setChkWildfood(chkWildfood.isChecked());
            typeResDao.setChkEsanfood(chkEsanfood.isChecked());
            typeResDao.setChkBuffet(chkBuffet.isChecked());

            benefitDao.setParking(chkParking.isChecked());
            benefitDao.setCreditCards(chkCreditCards.isChecked());
            benefitDao.setLiveMusic(chkLiveMusic.isChecked());
            benefitDao.setReservation(chkReservation.isChecked());
            benefitDao.setAlcohol(chkAlcohol.isChecked());
            mCallBack.onSuccessToAddBenefitsClick(typeResDao, benefitDao);
        }
    };
    protected View.OnClickListener OnBackPressClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCallBack.onBenefitsBackPress();
        }
    };


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            uri = data.getData();
            //   bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
            //    ivProfileAdd.setImageBitmap(bitmap);
            AddImageDao a = new AddImageDao();
            a.setUrl(uri);

            imageList.add(a);
            //   next = true;
        }
    }



    public static Fragment newInstance() {
        return new AddBenefitsResFragment();
    }
}
