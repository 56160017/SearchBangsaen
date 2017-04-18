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
import android.widget.Toast;

import com.buu.se.searchbangsaen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class RegisterEmailFragment extends Fragment {


    @BindView(R.id.txt_level1) TextView txtLevel1;
    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_pwd) EditText etPwd;
    @BindView(R.id.et_conf_pwd) EditText etConfPwd;
    @BindView(R.id.btn_submit) Button btnSubmit;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String userID;
    private String email;
    private String pwd;

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
                if (etEmail != null && etPwd != null) {
                    if (etPwd.getText().toString().equals(etConfPwd.getText().toString())) {
                        // CreateUser();
                        mCallBack.onSuccessToRegisterDetailClick(etEmail.getText().toString(), etPwd.getText().toString());
                    }
                }
            }
        });

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
