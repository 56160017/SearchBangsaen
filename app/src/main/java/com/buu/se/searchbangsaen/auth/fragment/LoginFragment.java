package com.buu.se.searchbangsaen.auth.fragment;


import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.buu.se.searchbangsaen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends Fragment {


    @BindView(R.id.btn_login) Button btnLogin;
    @BindView(R.id.btn_register) Button btnRegister;
    @BindView(R.id.et_user) EditText etUser;
    @BindView(R.id.et_pass) EditText etPass;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private onClickRegisterListener mCallBack;
    public interface onClickRegisterListener {
        void onSuccessToRegisterClick();
        void onSuccessToUserPageClick();

    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  etUser.getText();
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("usergetUid: ", user.getUid());

                } else {
                    // User is signed out
                    Log.d("onAuthStateChanged: ", "User is signed out");
                }
            }
        };



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  if(1 == 1){
                    SignInWithEmailAndPassword();
//                }else {
//                    Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
//                }

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onSuccessToRegisterClick();
            }
        });
    }
    private void SignInWithEmailAndPassword() {
        mAuth.signInWithEmailAndPassword(etUser.getText().toString(), etPass.getText().toString())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w( "signInWithEmail", task.getException().getMessage());
                            Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getActivity(), "Authentication True.", Toast.LENGTH_SHORT).show();
                            mCallBack.onSuccessToUserPageClick();
                        }
                        // ...
                    }
                });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBack = (onClickRegisterListener) context;

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
    public static Fragment newInstance() {
        return new LoginFragment();
    }

}
