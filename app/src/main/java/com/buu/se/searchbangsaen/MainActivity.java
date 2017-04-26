package com.buu.se.searchbangsaen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.auth.activity.AuthActivity;
import com.buu.se.searchbangsaen.editcategories.activity.EditPageActivity;
import com.buu.se.searchbangsaen.hotel_categories.activity.HotelActivity;
import com.buu.se.searchbangsaen.restaurant_categories.activity.RestaurantSearchActivity;
import com.buu.se.searchbangsaen.utils.CircleTransform;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.roger.catloadinglibrary.CatLoadingView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.civ_search) CircleImageView civSearch;
    @BindView(R.id.tv_login) TextView tvLogin;
    @BindView(R.id.btn_Restaurant) ImageView btnRestaurant;
    @BindView(R.id.btn_Hotel) CircleImageView btnHotel;
    @BindView(R.id.btn_Coffee) CircleImageView btnCoffee;
    @BindView(R.id.img_Gift) CircleImageView imgGift;
    @BindView(R.id.btn_Ws) CircleImageView btnWs;
    @BindView(R.id.img_Festival) CircleImageView imgFestival;
    @BindView(R.id.activity_main) RelativeLayout activityMain;
    @BindView(R.id.iv_profile) ImageView ivProfile;
    @BindView(R.id.tv_t) TextView tvT;
    @BindView(R.id.rl_profile) RelativeLayout rlProfile;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog progressDialog;
    private CatLoadingView mView;
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//progress
        mView = new CatLoadingView();
        mView.show(getSupportFragmentManager(), "MainActivity");

//firebase
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            }
        };
        mStorage = FirebaseStorage.getInstance().getReference();


        btnRestaurant.setOnClickListener(OnRestaurantClickListener);
        btnHotel.setOnClickListener(OnHotelClickListener);
        tvLogin.setOnClickListener(OnLoginClickListener);
        ivProfile.setOnClickListener(OnProfileClickListener);
    }


    @Override
    protected void onResume() {

        mView.dismiss();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            initProfile(user);
        }
        super.onResume();
    }


    private void CheckAuth() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d("CheckAuth: ", user.getUid());
            tvLogin.setVisibility(View.GONE);
            rlProfile.setVisibility(View.VISIBLE);

        } else {
            tvLogin.setVisibility(View.VISIBLE);
            rlProfile.setVisibility(View.GONE);
        }
    }

    private void initProfile(FirebaseUser user) {
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("users").child(user.getUid()).child("detail").child("pic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StorageReference filepath = mStorage.child("profile").child("" + dataSnapshot.getValue());
                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.with(MainActivity.this).load(uri.toString()).transform(new CircleTransform())
                                .placeholder(getResources().getDrawable(R.drawable.user_profile)).into(ivProfile);
                    }
                });
                //
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public View.OnClickListener OnRestaurantClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), RestaurantSearchActivity.class);
            startActivity(intent);
            //mView.show(getSupportFragmentManager(), "MainActivity");

        }
    };

    public View.OnClickListener OnHotelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), HotelActivity.class);
            startActivity(intent);

        }
    };

    public View.OnClickListener OnLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), AuthActivity.class);
            startActivity(intent);
        }
    };

    public View.OnClickListener OnProfileClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), EditPageActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        CheckAuth();

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
