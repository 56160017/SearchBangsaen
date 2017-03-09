package com.buu.se.searchbangsaen.searchcategories.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.buu.se.searchbangsaen.R;
import com.squareup.picasso.Picasso;

public class ShowImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        Log.d("onCreateTAG : ","eiei");
        ImageView ivItem = (ImageView) findViewById(R.id.ivItemBig);

        if(getIntent() != null) {
            String imageSrc = getIntent().getStringExtra("imageSrc");
            if(imageSrc != null) {
             //   ivItem.setImageResource(imageSrc);
                Picasso.with(this).load(imageSrc).into(ivItem);
            }else{
                Log.d( "testIvitem :","null");
            }
        }
    }
}
