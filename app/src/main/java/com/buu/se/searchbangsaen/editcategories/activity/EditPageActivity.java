package com.buu.se.searchbangsaen.editcategories.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import com.buu.se.searchbangsaen.R;

import com.buu.se.searchbangsaen.editcategories.fragment.EditDataPageFragment;
import com.buu.se.searchbangsaen.editcategories.fragment.EditHotelFragment;
import com.buu.se.searchbangsaen.editcategories.fragment.EditProfilePageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;

public class EditPageActivity extends AppCompatActivity implements yalantis.com.sidemenu.util.ViewAnimator.ViewAnimatorListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private yalantis.com.sidemenu.util.ViewAnimator viewAnimator;
    private LinearLayout linearLayout;
    private EditDataPageFragment editDataPageFragment;
    private EditProfilePageFragment editProfilePageFragment;
    private EditHotelFragment editHotelFragment;

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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
