package com.buu.se.searchbangsaen.auth.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.activity.AddHotelActivity;
import com.buu.se.searchbangsaen.add_categories.activity.AddRestaurantActivity;
import com.buu.se.searchbangsaen.auth.adapter.AuthMenuAdapter;
import com.buu.se.searchbangsaen.auth.dao.AuthDao;
import com.buu.se.searchbangsaen.auth.fragment.LoginFragment;
import com.buu.se.searchbangsaen.auth.fragment.LoginFragment.onClickRegisterListener;
import com.buu.se.searchbangsaen.auth.fragment.MainRegisterFragment;
import com.buu.se.searchbangsaen.auth.fragment.RegisterDetailFragment.onClickToPictureRegisterListener;
import com.buu.se.searchbangsaen.auth.fragment.RegisterEmailFragment;
import com.buu.se.searchbangsaen.auth.fragment.RegisterEmailFragment.onClickDetailRegisterListener;
import com.buu.se.searchbangsaen.auth.fragment.RegisterPictureFragment.onClickCreateDataRegisterListener;
import com.buu.se.searchbangsaen.editcategories.activity.EditPageActivity;
import com.buu.se.searchbangsaen.utils.SearchNonSwipeableViewPager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AuthActivity extends AppCompatActivity implements
        onClickRegisterListener
        , onClickDetailRegisterListener
        , onClickToPictureRegisterListener
        , onClickCreateDataRegisterListener {

    @BindView(R.id.fl_non_swipe_viewpager) SearchNonSwipeableViewPager flNonSwipeViewpager;
    private AuthDao mAuthDao;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private AuthMenuAdapter menuAdapter;

    private StorageReference mStorage;

    private ProgressDialog progressDialog;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        i = getIntent();
        CheckAuth();
        mAuthDao = new AuthDao();
        mAuthDao.setCategoriesID(i.getIntExtra("CategoriesID",0));
        menuAdapter = new AuthMenuAdapter(getSupportFragmentManager());
        flNonSwipeViewpager.setAdapter(menuAdapter);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
        mStorage = FirebaseStorage.getInstance().getReference();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void CheckAuth() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            if (i.getIntExtra("CategoriesID",0) == 1) {
                Log.d("CheckAuth: ", user.getUid());
                Intent i = new Intent(AuthActivity.this, AddRestaurantActivity.class);
                startActivity(i);
                finish();
            } else if (i.getIntExtra("CategoriesID",0) == 2) {
                Log.d("CheckAuth: ", user.getUid());
                Intent i = new Intent(AuthActivity.this, AddHotelActivity.class);
                startActivity(i);
                finish();
            } else{
                finish();
            }
        }else {
            Log.d("CheckAuth: ", "State: Sign out");
        }
    }
    @Override
    public void onBackPressed() {

        try {
            Fragment mFragmentStep = menuAdapter.getAuthFragment(flNonSwipeViewpager.getCurrentItem());
            if (mFragmentStep instanceof LoginFragment) {
                finish();
            } else if (mFragmentStep instanceof MainRegisterFragment) {
                // flNonSwipeViewpager.setCurrentItem(0,true);
                Fragment subMenuFragment = ((MainRegisterFragment) mFragmentStep).getViewpagerRegisterFragment();
                if (subMenuFragment instanceof RegisterEmailFragment) {
                    flNonSwipeViewpager.setCurrentItem(0, true);
                } else {
                    ((MainRegisterFragment) mFragmentStep).onBackStepFragment();
                }
            } else if (mFragmentStep instanceof MainRegisterFragment) {
                flNonSwipeViewpager.setCurrentItem(0, true);
            } else {
                super.onBackPressed();
            }
        } catch (ClassCastException e) {
            super.onBackPressed();

        }
    }

    @Override
    public void onSuccessToRegisterClick() {
        // Fragment mFragment = menuAdapter.getAuthFragment(1);
        //   if (mFragment instanceof RegisterEmailFragment) {
        flNonSwipeViewpager.setCurrentItem(1, true);

        //    }
    }

    @Override
    public void onSuccessToRegisterDetailClick(String email, String pwd) {
       // CreateUser(email, pwd);
        mAuthDao.setUid(email);
        mAuthDao.setPwd(pwd);
        Fragment mFragment = menuAdapter.getAuthFragment(1);
        if (mFragment instanceof MainRegisterFragment) {
            ((MainRegisterFragment) mFragment).changeToRegisterDetailClick();
        }

    }

    @Override
    public void onSuccessToRegisterPictureClick(String name, String sname, String phone, String email) {
        mAuthDao.setName(name);
        mAuthDao.setSname(sname);
        mAuthDao.setNumberphone(phone);
        mAuthDao.setEmail(email);

        Fragment mFragment = menuAdapter.getAuthFragment(1);
        if (mFragment instanceof MainRegisterFragment) {
            ((MainRegisterFragment) mFragment).changeToRegisterPictureClick();
        }
    }

    @Override
    public void onSuccessToCreateDataClick(Uri picUri) {
        mAuthDao.setUri(picUri);
        Log.d("onSuccessToCreateData: ", "onSuccess");
        CreateUser();
    }


    @Override
    public void onSuccessToUserPageClick() { // หน้า login
        CheckAuth();
    }

    private void CreateUser() {

        mAuth.createUserWithEmailAndPassword(mAuthDao.getUid(), mAuthDao.getPwd())
                .addOnCompleteListener(AuthActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(AuthActivity.this, "Add failed.", Toast.LENGTH_SHORT).show();
                            Log.d("onCompleteRegis: ",task.toString());
                      /*      userID =  etEmail.getText().toString();
                            pwd = etPwd.getText().toString();*/
                        } else {
                            Toast.makeText(AuthActivity.this, "Add OK.", Toast.LENGTH_SHORT).show();
                            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                            mRootRef.child("users").child(task.getResult().getUser().getUid()).child("detail").child("userID").setValue(mAuthDao.getUid());
                            mRootRef.child("users").child(task.getResult().getUser().getUid()).child("detail").child("pwd").setValue(mAuthDao.getPwd());
                            mRootRef.child("users").child(task.getResult().getUser().getUid()).child("detail").child("name").setValue(mAuthDao.getName());
                            mRootRef.child("users").child(task.getResult().getUser().getUid()).child("detail").child("surname").setValue(mAuthDao.getSname());
                            mRootRef.child("users").child(task.getResult().getUser().getUid()).child("detail").child("phone").setValue(mAuthDao.getNumberphone());
                            mRootRef.child("users").child(task.getResult().getUser().getUid()).child("detail").child("email").setValue(mAuthDao.getEmail());
                            String photo_uuid = "pt-" + UUID.randomUUID().toString();

                          /*  if (mAuthDao.getCategoriesID() != null) {
                               // mRootRef.child("users-categories").child(task.getResult().getUser().getUid()).child(mAuthDao.getCategoriesID()).setValue(uuid);
                            }*/

                            mRootRef.child("users").child(task.getResult().getUser().getUid()).child("detail").child("pic").setValue(photo_uuid);
                            StorageReference filepath = mStorage.child("profile").child(photo_uuid);
                            filepath.putFile(mAuthDao.getUri()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(AuthActivity.this, "upload pic. Good", Toast.LENGTH_SHORT).show();
                                }

                            });


                            Intent i = new Intent(AuthActivity.this, EditPageActivity.class);
                            startActivity(i);
                            finish();
                        }

                    }
                });

    }
    private void Detail(final String name, final String sname,String phone,String email) {

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

    @Override
    protected void onDestroy() {
        mAuthDao = null;
        super.onDestroy();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
