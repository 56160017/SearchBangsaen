package com.buu.se.searchbangsaen.auth.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterEmailFragment extends Fragment {


    @BindView(R.id.txt_level1) TextView txtLevel1;
    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_pwd) EditText etPwd;
    @BindView(R.id.et_conf_pwd) EditText etConfPwd;
    @BindView(R.id.btn_submit) Button btnSubmit;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private onClickDetailRegisterListener mCallBack;

    public interface onClickDetailRegisterListener {
        void onSuccessToRegisterDetailClick(String email, String pwd);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBack = (onClickDetailRegisterListener) getActivity();

    }

    public RegisterEmailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("usergetUid: ", user.getUid());
                    Log.d("onAuthStateChanged: ", "User is signed in");
                } else {
                    // User is signed out
                    Log.d("onAuthStateChanged: ", "User is signed out");
                }
            }
        };
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isValidEmail(etEmail.getText().toString())) {
                    etEmail.setError("Email is invalid");
                    return;
                }
                if (!isValidPassword(etPwd.getText().toString())) {
                    etPwd.setError("Password is too short (minimum is 6 characters)");
                    return;
                }
                if (!etPwd.getText().toString().equals(etConfPwd.getText().toString())) {
                        // CreateUser();
                    etConfPwd.setError("Password does not match the confirm password.");
                    return;
                    }
                mCallBack.onSuccessToRegisterDetailClick(etEmail.getText().toString(), etPwd.getText().toString());
                }
            }
        );

    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 5) {
            return true;
        }
        return false;
    }
    public static Fragment newInstance() {
        return new RegisterEmailFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
