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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends Fragment {


    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.forgot_pass)
    TextView forgotPass;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private onClickRegisterListener mCallBack;


    public interface onClickRegisterListener {
        void onSuccessToRegisterClick();
        void onSuccessToUserPageClick();
        void onSuccessToForgetPassword();

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

    private void checkValidate() {

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

                if (!isValidEmail(etUser.getText().toString())) {
                    etUser.setError("อีเมลไม่ถูกต้อง");
                    return;
                }
                if (!isValidPassword(etPass.getText().toString())) {
                    etPass.setError("รหัสผ่านไม่ถูกต้อง");
                    return;
                }else
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

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onSuccessToForgetPassword();
            }
        });
    }

    private void SignInWithEmailAndPassword() {
        mAuth.signInWithEmailAndPassword(etUser.getText().toString(), etPass.getText().toString())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("signInWithEmail", task.getException().getMessage());
                            Toast.makeText(getActivity(), "อีเมลหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(), "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_SHORT).show();
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
