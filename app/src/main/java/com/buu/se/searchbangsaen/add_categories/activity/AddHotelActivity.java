package com.buu.se.searchbangsaen.add_categories.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.adapter.AddHotelAdapter;
import com.buu.se.searchbangsaen.add_categories.dao.AddHotelDao;
import com.buu.se.searchbangsaen.add_categories.fragment.AddDetailHotelFragment;
import com.buu.se.searchbangsaen.add_categories.fragment.AddLatLngHotelFragment;
import com.buu.se.searchbangsaen.add_categories.fragment.AddPictureHotelFragment;
import com.buu.se.searchbangsaen.utils.SearchNonSwipeableViewPager;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddHotelActivity extends AppCompatActivity implements AddDetailHotelFragment.onAddDetailSuccessClickNextListener,
        AddLatLngHotelFragment.onAddMapSuccessClickNextListener,
        AddPictureHotelFragment.onAddpictureSuccessClickNextListener{

    @BindView(R.id.fl_add_swipe_viewpager) SearchNonSwipeableViewPager flAddSwipeViewpager;
    private AddHotelAdapter addAdapter;
    private AddHotelDao addHotelDao;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);
        ButterKnife.bind(this);
        addAdapter = new AddHotelAdapter(getSupportFragmentManager());
        flAddSwipeViewpager.setAdapter(addAdapter);
        //addHotelDao = new AddHotelDao();
        addHotelDao = new AddHotelDao();
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
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
    public void onSuccessToAddDetailClick(String etAddHotel, String etAddPhone, String etEmtryRoom, String etPriceF, String etPriceT, String etAddress) {
        addHotelDao.setNameHotel(etAddHotel);
        addHotelDao.setHotelPhone(etAddPhone);
        addHotelDao.setHotelEmtryRoom(etEmtryRoom);
        addHotelDao.setHotelPrice_f(etPriceF);
        addHotelDao.setHotelPrice_t(etPriceT);
        addHotelDao.setHotelAddress(etAddress);

        flAddSwipeViewpager.setCurrentItem(1);
    }

    @Override
    public void onBackClick() {
        onBackPressed();
    }


    @Override
    public void onSuccessToAddMapClick(LatLng selectLatLng) {
        addHotelDao.setHotelLatLng(selectLatLng);

        flAddSwipeViewpager.setCurrentItem(2);
    }

    @Override
    public void onSuccessToAddPictureClick() {
        AddDatatoFirebase();
        finish();
    }
    @Override
    public void onBackPressed() {
        try {
            Fragment mFragmentStep = addAdapter.getHotelFragment(flAddSwipeViewpager.getCurrentItem());
            if (mFragmentStep instanceof AddDetailHotelFragment) {
                finish();
            } else if (mFragmentStep instanceof AddLatLngHotelFragment) {
                flAddSwipeViewpager.setCurrentItem(0, true);
            } else if (mFragmentStep instanceof AddPictureHotelFragment) {
                flAddSwipeViewpager.setCurrentItem(1, true);
            }
        } catch (ClassCastException e) {
            super.onBackPressed();
        }
    }

    private void AddDatatoFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.getUid() != null) {
            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

            String uuid = UUID.randomUUID().toString();
            mRootRef.child("hotel").child(uuid).child("uid").setValue(user.getUid());
            mRootRef.child("hotel").child(uuid).child("name").setValue(addHotelDao.getNameHotel());
            mRootRef.child("hotel").child(uuid).child("phone").setValue(addHotelDao.getHotelPhone());
            mRootRef.child("hotel").child(uuid).child("emtry_room").setValue(addHotelDao.getHotelEmtryRoom());
            mRootRef.child("hotel").child(uuid).child("price_f").setValue(addHotelDao.getHotelPrice_f());
            mRootRef.child("hotel").child(uuid).child("price_t").setValue(addHotelDao.getHotelPrice_t());

            mRootRef.child("hotel").child(uuid).child("address").setValue(addHotelDao.getHotelAddress());
            mRootRef.child("hotel").child(uuid).child("latitude").setValue("" + addHotelDao.getHotelLatLng().latitude);
            mRootRef.child("hotel").child(uuid).child("longitude").setValue("" + addHotelDao.getHotelLatLng().longitude);
            //    mRootRef.child("users-categories").child(user.getUid()).child("resta").setValue(mAuthDao.getUid());


            finish();


        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
