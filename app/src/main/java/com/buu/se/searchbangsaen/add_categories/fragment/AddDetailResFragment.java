package com.buu.se.searchbangsaen.add_categories.fragment;


import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.dao.DateDao;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddDetailResFragment extends Fragment {


    @BindView(R.id.et_time_open) Button etTimeOpen;
    @BindView(R.id.et_time_close) Button etTimeClose;
    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.btn_submit) Button btnSubmit;

    @BindView(R.id.et_add_phone) EditText etAddPhone;
    @BindView(R.id.et_add_food) EditText etAddFood;
    @BindView(R.id.chkSun) CheckBox chkSun;
    @BindView(R.id.chkMonday) CheckBox chkMonday;
    @BindView(R.id.chktuesday) CheckBox chktuesday;
    @BindView(R.id.chkWed) CheckBox chkWed;
    @BindView(R.id.chkThursday) CheckBox chkThursday;
    @BindView(R.id.chkFriday) CheckBox chkFriday;
    @BindView(R.id.chkSaturday) CheckBox chkSaturday;
    @BindView(R.id.et_address) EditText etAddress;
    private onAddDetailSuccessClickNextListener mCallBack;
    private String TimeOpen;
    private String TimeClose;
    private DateDao dateDao;
    private String sDate;


    public interface onAddDetailSuccessClickNextListener {
        void onSuccessToAddDetailClick(String NameFood,String PhoneNumber
                                        ,String TimeOpen,String TimeClose,DateDao dateDao,String Date
                                        ,String Address);
        void onDetailBackPress();
    }

    public AddDetailResFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_detail_res, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateDao = new DateDao();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validate();
                AddDataChkDate();
                AddDataToActivity();
            }
        });


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mCallBack.onDetailBackPress();

            }
        });


        etTimeOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String sHours;
                        String sMinutes;
                        if (selectedMinute < 10) {
                            sMinutes = String.format("%02d", selectedMinute);
                        } else {
                            sMinutes = "" + selectedMinute;
                        }
                        if (selectedHour < 10) {
                            sHours = String.format("%02d", selectedHour);
                        } else {
                            sHours = "" + selectedHour;
                        }
                        TimeOpen = sHours + "." + sMinutes;
                        etTimeOpen.setText(sHours + "." + sMinutes + " น.");
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        etTimeClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String sHours;
                        String sMinutes;
                        if (selectedMinute < 10) {
                            sMinutes = String.format("%02d", selectedMinute);
                        } else {
                            sMinutes = "" + selectedMinute;
                        }

                        if (selectedHour < 10) {
                            sHours = String.format("%02d", selectedHour);
                        } else {
                            sHours = "" + selectedHour;
                        }

                        TimeClose = sHours + "." + sMinutes;
                        etTimeClose.setText(sHours + "." + sMinutes + " น.");
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }


    private void AddDataChkDate() {
       // dateDao.setSun(chkSun.isChecked());
        dateDao.setSun(chkSun.isChecked());
        dateDao.setMonday(chkMonday.isChecked());
        dateDao.setTuesday(chktuesday.isChecked());
        dateDao.setWed(chkWed.isChecked());
        dateDao.setThursday(chkThursday.isChecked());
        dateDao.setFriday(chkFriday.isChecked());
        dateDao.setSaturday(chkSaturday.isChecked());
        sDate = "";
        if(chkSun.isChecked()){
            sDate += "อา. ";
        }
        if(chkMonday.isChecked()){
            sDate += "จ. ";
        }
        if(chktuesday.isChecked()){
            sDate += "อ. ";
        }
        if(chkWed.isChecked()){
            sDate += "พ. ";
        }
        if(chkThursday.isChecked()){
            sDate += "พฤ. ";
        }
        if(chkFriday.isChecked()){
            sDate += "ศ. ";
        }
        if(chkSaturday.isChecked()){
            sDate += "ส.";
        }


    }

    private void AddDataToActivity() {
        mCallBack.onSuccessToAddDetailClick(
                etAddFood.getText().toString()
                , etAddPhone.getText().toString()
                , TimeOpen, TimeClose, dateDao,sDate
                , etAddress.getText().toString()
                );
    }
    private void Validate(){
        /*if(etAddFood.getText() == null || etAddFood.getText().toString() == ""){
            etAddFood.setHint("กรุณากรอก");
            etAddFood.setHintTextColor(getResources().getColor(R.color.Text_error));

            return true;
        }

       return false;*/
    }


    public static Fragment newInstance() {
        return new AddDetailResFragment();
    }
}
