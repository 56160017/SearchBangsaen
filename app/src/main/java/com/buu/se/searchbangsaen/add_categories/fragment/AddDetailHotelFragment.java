package com.buu.se.searchbangsaen.add_categories.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.dao.BenefitHotelDao;
import com.buu.se.searchbangsaen.add_categories.dao.RelaxDao;
import com.buu.se.searchbangsaen.hotel_categories.dao.BenefitsHotelDao;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddDetailHotelFragment extends Fragment {

    @BindView(R.id.btn_submit) Button btnSubmit;
    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.et_add_hotel) EditText etAddHotel;
    @BindView(R.id.et_add_phone) EditText etAddPhone;
    @BindView(R.id.et_emtry_room) EditText etEmtryRoom;
    @BindView(R.id.et_price_f) EditText etPriceF;
    @BindView(R.id.et_price_t) EditText etPriceT;
    @BindView(R.id.et_address) EditText etAddress;

    @BindView(R.id.chk_swim) CheckBox chkSwim;
    @BindView(R.id.chk_fitness) CheckBox chkFitness;
    @BindView(R.id.chk_playground) CheckBox chkPlayground;
    @BindView(R.id.chk_golf) CheckBox chkGolf;

    @BindView(R.id.chk_internat) CheckBox chkInternat;
    @BindView(R.id.chk_shop) CheckBox chkShop;
    @BindView(R.id.chk_dry) CheckBox chkDry;
    @BindView(R.id.chk_parking) CheckBox chkParking;
    @BindView(R.id.chk_taxi) CheckBox chkTaxi;

    private RelaxDao relaxDao;
    private BenefitHotelDao addbenefitDao;


    private onAddDetailSuccessClickNextListener mCallBack;

    public interface onAddDetailSuccessClickNextListener {
        void onSuccessToAddDetailClick(String etAddHotel,
                                       String etAddPhone,
                                       String etEmtryRoom,
                                       String etPriceF,
                                       String etPriceT,
                                       String etAddress,
                                       RelaxDao relaxDao,
                                       BenefitHotelDao addbenefitDao);

        void onBackClick();
    }

    public AddDetailHotelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBack = (onAddDetailSuccessClickNextListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_detail_hotel, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        relaxDao = new RelaxDao();
        addbenefitDao = new BenefitHotelDao();


        btnSubmit.setOnClickListener(OnbtnSubmitClickListener);
        ivBack.setOnClickListener(OnbtnBackClickListener);
    }

    private View.OnClickListener OnbtnSubmitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setDataToActivity();
            AddDataToActivity();
        }
    };
    private View.OnClickListener OnbtnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // ออก
            mCallBack.onBackClick();
        }
    };
    private void setDataToActivity() {
        relaxDao.setSwim(chkSwim.isChecked());
        relaxDao.setFitness(chkFitness.isChecked());
        relaxDao.setPlayground(chkPlayground.isChecked());
        relaxDao.setGolf(chkGolf.isChecked());

        addbenefitDao.setChkParking(chkParking.isChecked());
        addbenefitDao.setChkDry(chkDry.isChecked());
        addbenefitDao.setChkInternat(chkInternat.isChecked());
        addbenefitDao.setChkShop(chkShop.isChecked());
        addbenefitDao.setChkTaxi(chkTaxi.isChecked());
    }

    private void AddDataToActivity() {
        mCallBack.onSuccessToAddDetailClick(
                etAddHotel.getText().toString(),
                etAddPhone.getText().toString(),
                etEmtryRoom.getText().toString(),
                etPriceF.getText().toString(),
                etPriceT.getText().toString(),
                etAddress.getText().toString(),
                relaxDao,addbenefitDao);
    }

    public static Fragment newInstance() {
        return new AddDetailHotelFragment();
    }
}
