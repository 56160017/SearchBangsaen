package com.buu.se.searchbangsaen.searchcategories.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ShowImageActivity extends AppCompatActivity {

    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.tvNameTitle) TextView tvNameTitle;
    @BindView(R.id.ivItemBig) ImageView ivItemBig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ButterKnife.bind(this);


        if (getIntent() != null) {
            tvNameTitle.setText( getIntent().getStringExtra("tvname"));
            String imageSrc = getIntent().getStringExtra("imageSrc");
            if (imageSrc != null) {
                //   ivItem.setImageResource(imageSrc);
                Picasso.with(this).load(imageSrc).into(ivItemBig);
            } else {
                Log.d("testIvitem :", "null");
            }
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
