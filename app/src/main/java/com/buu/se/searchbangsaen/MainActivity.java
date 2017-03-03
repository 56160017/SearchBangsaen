package com.buu.se.searchbangsaen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.buu.se.searchbangsaen.searchcategories.activity.RestaurantSearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnRestaurant) ImageButton btnRestaurant;
    @BindView(R.id.btnHotel) ImageButton btnHotel;
    @BindView(R.id.btnCoffee) ImageButton btnCoffee;
    @BindView(R.id.btnGift) ImageButton btnGift;
    @BindView(R.id.btnWS) ImageButton btnWS;
    @BindView(R.id.btnFestival) ImageButton btnFestival;
    @BindView(R.id.activity_main) LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Intent intent = new Intent(getApplicationContext(), CardRestaurantSearchActivity.class);
                Intent intent = new Intent(getApplicationContext(), RestaurantSearchActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
