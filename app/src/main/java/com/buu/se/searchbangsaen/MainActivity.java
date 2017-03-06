package com.buu.se.searchbangsaen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.searchcategories.activity.RestaurantSearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.civ_search) CircleImageView civSearch;
    @BindView(R.id.tv_login) TextView tvLogin;
    @BindView(R.id.tv_report) TextView tvReport;
    @BindView(R.id.btn_Restaurant) CircleImageView btnRestaurant;
    @BindView(R.id.img_Hotel) CircleImageView imgHotel;
    @BindView(R.id.btn_Coffee) CircleImageView btnCoffee;
    @BindView(R.id.img_Gift) CircleImageView imgGift;
    @BindView(R.id.btn_Ws) CircleImageView btnWs;
    @BindView(R.id.img_Festival) CircleImageView imgFestival;
    @BindView(R.id.activity_main) LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvLogin.setPaintFlags(tvLogin.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        tvReport.setPaintFlags(tvReport.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        tvLoginOnClickListener();
        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    Intent intent = new Intent(getApplicationContext(), CardRestaurantSearchActivity.class);
                Intent intent = new Intent(getApplicationContext(), RestaurantSearchActivity.class);
                startActivity(intent);
            }
        });

    }

    private void tvLoginOnClickListener() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
