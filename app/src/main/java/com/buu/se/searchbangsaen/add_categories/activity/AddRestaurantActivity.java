package com.buu.se.searchbangsaen.add_categories.activity;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.dao.AddRestaurantDao;
import com.buu.se.searchbangsaen.add_categories.adapter.AddRestaurantAdapter;
import com.buu.se.searchbangsaen.add_categories.dao.BenefitDao;
import com.buu.se.searchbangsaen.add_categories.dao.DateDao;
import com.buu.se.searchbangsaen.add_categories.dao.TypeResDao;
import com.buu.se.searchbangsaen.add_categories.fragment.AddBenefitsResFragment;
import com.buu.se.searchbangsaen.add_categories.fragment.AddDetailResFragment;
import com.buu.se.searchbangsaen.add_categories.fragment.AddLatLngResFragment;
import com.buu.se.searchbangsaen.utils.SearchNonSwipeableViewPager;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddRestaurantActivity extends AppCompatActivity implements
        AddDetailResFragment.onAddDetailSuccessClickNextListener,
        AddLatLngResFragment.onAddMapSuccessClickNextListener,
        AddBenefitsResFragment.onAddBenefitsSuccessClickNextListener {

    @BindView(R.id.fl_add_swipe_viewpager) SearchNonSwipeableViewPager flAddSwipeViewpager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private AddRestaurantAdapter addAdapter;
    private AddRestaurantDao addRestaurantDao;


    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        ButterKnife.bind(this);
        addAdapter = new AddRestaurantAdapter(getSupportFragmentManager());
        flAddSwipeViewpager.setAdapter(addAdapter);
        addRestaurantDao = new AddRestaurantDao();
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
    public void onSuccessToAddDetailClick(String NameFood, String PhoneNumber, String TimeOpen,
                                          String TimeClose, DateDao dateDao, String Date, String Address) {
       addRestaurantDao.setNameRestaurant(NameFood);
        addRestaurantDao.setResPhone(PhoneNumber);
        addRestaurantDao.setResOpen(TimeOpen);
        addRestaurantDao.setResClose(TimeClose);
        addRestaurantDao.setResDateDao(dateDao);
        addRestaurantDao.setResDate(Date);
        addRestaurantDao.setResAddress(Address);

        flAddSwipeViewpager.setCurrentItem(1, true);
    }

    @Override
    public void onDetailBackPress() {
            onBackPressed();
    }

    @Override
    public void onSuccessToAddMapClick(LatLng selectLatLng) {
        addRestaurantDao.setResLatLng(selectLatLng);
        flAddSwipeViewpager.setCurrentItem(2, true);
    }

    @Override
    public void onLatLngBackPress() {
        onBackPressed();
    }


    @Override
    public void onSuccessToAddBenefitsClick(TypeResDao typeResDao, BenefitDao benefitDao) {
        addRestaurantDao.setResTypeDao(typeResDao);
        addRestaurantDao.setResBenefitDao(benefitDao);
        AddDatatoFirebase();
    }

    @Override
    public void onBenefitsBackPress() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        try {
            Fragment mFragmentStep = addAdapter.getResFragment(flAddSwipeViewpager.getCurrentItem());
            if (mFragmentStep instanceof AddDetailResFragment) {
                finish();
            } else if (mFragmentStep instanceof AddLatLngResFragment) {
                flAddSwipeViewpager.setCurrentItem(0, true);
            } else if (mFragmentStep instanceof AddBenefitsResFragment) {
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
            mRootRef.child("restaurant").child(uuid).child("uid").setValue(user.getUid());
            mRootRef.child("restaurant").child(uuid).child("name").setValue(addRestaurantDao.getNameRestaurant());
            mRootRef.child("restaurant").child(uuid).child("phone").setValue(addRestaurantDao.getResPhone());
            mRootRef.child("restaurant").child(uuid).child("time-open").setValue(addRestaurantDao.getResOpen());
            mRootRef.child("restaurant").child(uuid).child("time-close").setValue(addRestaurantDao.getResClose());
            mRootRef.child("restaurant").child(uuid).child("dates-open").setValue(addRestaurantDao.getResDate());
            mRootRef.child("restaurant").child(uuid).child("address").setValue(addRestaurantDao.getResAddress());

            //date Dao
            mRootRef.child("restaurant").child(uuid).child("dates").child("sunday").setValue(String.valueOf(addRestaurantDao.getResDateDao().isSun()));
            mRootRef.child("restaurant").child(uuid).child("dates").child("monday").setValue(String.valueOf(addRestaurantDao.getResDateDao().isMonday()));
            mRootRef.child("restaurant").child(uuid).child("dates").child("tuesday").setValue(String.valueOf(addRestaurantDao.getResDateDao().isTuesday()));
            mRootRef.child("restaurant").child(uuid).child("dates").child("wednesday").setValue(String.valueOf(addRestaurantDao.getResDateDao().isWed()));
            mRootRef.child("restaurant").child(uuid).child("dates").child("thursday").setValue(String.valueOf(addRestaurantDao.getResDateDao().isThursday()));
            mRootRef.child("restaurant").child(uuid).child("dates").child("friday").setValue(String.valueOf(addRestaurantDao.getResDateDao().isFriday()));
            mRootRef.child("restaurant").child(uuid).child("dates").child("saturday").setValue(String.valueOf(addRestaurantDao.getResDateDao().isSaturday()));

            //type food
            mRootRef.child("restaurant").child(uuid).child("typefoods").child("sea-food").setValue(String.valueOf(addRestaurantDao.getResTypeDao().isChkSea()));
            mRootRef.child("restaurant").child(uuid).child("typefoods").child("single-food").setValue(String.valueOf(addRestaurantDao.getResTypeDao().isChkSingleFood()));
            mRootRef.child("restaurant").child(uuid).child("typefoods").child("thai-food").setValue(String.valueOf(addRestaurantDao.getResTypeDao().isChkThai()));
            mRootRef.child("restaurant").child(uuid).child("typefoods").child("wild-food").setValue(String.valueOf(addRestaurantDao.getResTypeDao().isChkWildfood()));
            mRootRef.child("restaurant").child(uuid).child("typefoods").child("esan-food").setValue(String.valueOf(addRestaurantDao.getResTypeDao().isChkEsanfood()));
            mRootRef.child("restaurant").child(uuid).child("typefoods").child("buffet").setValue(String.valueOf(addRestaurantDao.getResTypeDao().isChkBuffet()));

            //benefit
            mRootRef.child("restaurant").child(uuid).child("benefits").child("parking").setValue(String.valueOf(addRestaurantDao.getResBenefitDao().isParking()));
            mRootRef.child("restaurant").child(uuid).child("benefits").child("creditcards").setValue(String.valueOf(addRestaurantDao.getResBenefitDao().isCreditCards()));
            mRootRef.child("restaurant").child(uuid).child("benefits").child("livemusic").setValue(String.valueOf(addRestaurantDao.getResBenefitDao().isLiveMusic()));
            mRootRef.child("restaurant").child(uuid).child("benefits").child("reservation").setValue(String.valueOf(addRestaurantDao.getResBenefitDao().isReservation()));
            mRootRef.child("restaurant").child(uuid).child("benefits").child("alcohol").setValue(String.valueOf(addRestaurantDao.getResBenefitDao().isAlcohol()));

            // latitude longitude
            mRootRef.child("restaurant").child(uuid).child("latitude").setValue("" + addRestaurantDao.getResLatLng().latitude);
            mRootRef.child("restaurant").child(uuid).child("longitude").setValue("" + addRestaurantDao.getResLatLng().longitude);
          
            Query query = mRootRef.child("restaurant").orderByChild("uid").equalTo(user.getUid());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        // dataSnapshot is the "issue" node with all children with id 0
                        for (DataSnapshot issue : dataSnapshot.getChildren()) {

                            // do something with the individual "issues"
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            finish();


        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
