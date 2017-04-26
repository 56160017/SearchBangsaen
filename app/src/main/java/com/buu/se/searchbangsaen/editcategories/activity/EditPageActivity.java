package com.buu.se.searchbangsaen.editcategories.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.buu.se.searchbangsaen.MainActivity;
import com.buu.se.searchbangsaen.R;

import com.buu.se.searchbangsaen.add_categories.dao.AddRestaurantDao;
import com.buu.se.searchbangsaen.add_categories.fragment.AddDetailResFragment;
import com.buu.se.searchbangsaen.editcategories.adapter.EditPageShopInAccountAdapter;
import com.buu.se.searchbangsaen.editcategories.fragment.EditDataPageFragment;
import com.buu.se.searchbangsaen.editcategories.fragment.EditHotelFragment;
import com.buu.se.searchbangsaen.editcategories.fragment.EditProfilePageFragment;
import com.buu.se.searchbangsaen.editcategories.fragment.EditRestarantFragment;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;

public class EditPageActivity extends AppCompatActivity implements yalantis.com.sidemenu.util.ViewAnimator.ViewAnimatorListener
,EditPageShopInAccountAdapter.onEditShopSuccessClickNextListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private yalantis.com.sidemenu.util.ViewAnimator viewAnimator;
    private LinearLayout linearLayout;
    private EditDataPageFragment editDataPageFragment;
    private EditProfilePageFragment editProfilePageFragment;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditHotelFragment editHotelFragment;
    private StorageReference mStorage;
    private Uri mUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);
        ButterKnife.bind(this);

        editDataPageFragment = EditDataPageFragment.newInstance();
        editProfilePageFragment = EditProfilePageFragment.newInstance();
        editHotelFragment = EditHotelFragment.newInstance();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, editDataPageFragment)
                .commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        //close tab
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        setActionBar();
        createMenuList();
        viewAnimator = new yalantis.com.sidemenu.util.ViewAnimator<>(this, list, editDataPageFragment, drawerLayout, this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
        mStorage = FirebaseStorage.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
           /* initProfile(user);*/
        }


    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem("CloseTab", R.drawable.arrow_left_pink);
        list.add(menuItem0);
        SlideMenuItem menuItem1 = new SlideMenuItem("EditPage", R.drawable.shop_white);
        list.add(menuItem1);
        SlideMenuItem menuItem2 = new SlideMenuItem("Profile", R.drawable.user_white);
        list.add(menuItem2);
    }

    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My title");
    //    toolbar.setTitleTextColor(00000000);
        toolbar.getBackground().setAlpha(0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.logout:
                finish();
                mAuth.signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition, String name) {
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(yalantis.com.sidemenu.util.ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
        animator.start();
        if(name == "Profile"){
            editProfilePageFragment = com.buu.se.searchbangsaen.editcategories.fragment.EditProfilePageFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, editProfilePageFragment).commit();
            return editProfilePageFragment;
        }else if(name == "EditPage"){
            editDataPageFragment = EditDataPageFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, editDataPageFragment).commit();
            return editDataPageFragment;
        }else if(name == "Logout"){
            finish();
            return editDataPageFragment;
        }else{
            editDataPageFragment = EditDataPageFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, editDataPageFragment).commit();
            return editDataPageFragment;
        }
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case "CloseTab":
                return screenShotable;
            case "Profile":
                return replaceFragment(screenShotable, position,"Profile");
            case "EditPage":
                return replaceFragment(screenShotable, position,"EditPage");
            case "Logout":
                return replaceFragment(screenShotable, position,"Logout");
            default:
                return screenShotable;
        }
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();
    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
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
 /*   private void initProfile(FirebaseUser user) {
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("users").child(user.getUid()).child("detail").child("pic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StorageReference filepath = mStorage.child("profile").child("" + dataSnapshot.getValue());
                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        mUri = uri;
                    *//* Picasso.with(EditPageActivity.this).load(uri.toString()).transform(new CircleTransform())
                                .placeholder(getResources().getDrawable(R.drawable.user_profile)).into(ivProfile);*//*
                    }
                });
                //
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onSuccessToEditClick(AddRestaurantDao addRestaurantDao) {
        Log.d( "onSuccessToEditClick: ","test");

        EditRestarantFragment myFragment = new EditRestarantFragment(addRestaurantDao);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, myFragment);
        transaction.commit();
    }


}
