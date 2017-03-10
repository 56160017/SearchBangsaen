package com.buu.se.searchbangsaen.searchcategories.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.searchcategories.adapter.BenefitsAdapter;
import com.buu.se.searchbangsaen.searchcategories.adapter.RecyclerImageAdapter;
import com.buu.se.searchbangsaen.searchcategories.dao.ImageDao;
import com.buu.se.searchbangsaen.searchcategories.dao.RestaurantDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class DetailRestaurantActivity extends AppCompatActivity {

    @BindView(R.id.tvNameTitle) TextView tvNameRes;
    @BindView(R.id.tvTitle) RelativeLayout tvTitle;
    @BindView(R.id.tvName) TextView tvName;
    //    @BindView(R.id.tvTypeFood) TextView tvTypeFood;
    @BindView(R.id.tvDate) TextView tvDate;
    @BindView(R.id.rlDetail) RelativeLayout rlDetail;
    @BindView(R.id.tvImg) TextView tvImg;
    @BindView(R.id.rvImgs) RecyclerView rvImgs;
    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.tv_phone) TextView tvPhone;
    @BindView(R.id.tv_location) TextView tvLocation;
    @BindView(R.id.gv_benefit) GridView gvBenefit;
    @BindView(R.id.tv_type_food) TextView tvTypeFood;

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerImageAdapter mAdapter;
    private RestaurantDao restaurantListDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);
        ButterKnife.bind(this);


        //ส่งค่าจากหน้าร้านอาหารว่าเป็นร้านอาหารไหน
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("key", 0);
        restaurantListDao = mIntent.getParcelableExtra("data");

        rvImgs.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvImgs.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerImageAdapter(DetailRestaurantActivity.this, getImageDao(intValue),restaurantListDao.getName());
        rvImgs.setAdapter(mAdapter);

        String text = getStringTypeFood();
        tvTypeFood.setText(text);

        gvBenefit.setNumColumns(3);
        gvBenefit.setAdapter(new BenefitsAdapter(this, restaurantListDao.getBenefitsDao()));

        tvNameRes.setText(restaurantListDao.getName());
        tvName.setText(restaurantListDao.getName());
        tvDate.setText(restaurantListDao.getDay() + " " + restaurantListDao.getOpen() + " - " + restaurantListDao.getClose());
        tvPhone.setText(restaurantListDao.getContact());
        tvLocation.setText(restaurantListDao.getLocation());

        ivBack.setOnClickListener(OnClickBackListener);
    }

    @NonNull
    private String getStringTypeFood() {
        String text = "";
        int i = 0;
        while (i < restaurantListDao.getTfDao().size()) {
            if (i > 0) text += ", ";
            text += restaurantListDao.getTfDao().get(i);
            i++;
        }
        return text;
    }

    @NonNull
    private List<ImageDao> getImageDao(int intValue) {
        List<ImageDao> imageList = new ArrayList<>();
        if (intValue == 0) {
            imageList.add(new ImageDao(1, "https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/403484_156009281197295_536660032_n.jpg?oh=a201d4b0399a80cc7535db8f50349334&oe=593FD711"));
            imageList.add(new ImageDao(2, "https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/14364713_894542120677337_1486207317892185568_n.jpg?oh=54a4f740ecd3ad3402d8cb1dec7b60b8&oe=59253DE7"));
            imageList.add(new ImageDao(3, "https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/12994476_815475631917320_5597565369867738509_n.jpg?oh=55d4d4d53a2da7dbd3d3f5a2b30a0601&oe=594514A0"));
            imageList.add(new ImageDao(4, "https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/12523937_811505465647670_6529723795513221531_n.jpg?oh=4c609c39568354fc58b26d050de9b56a&oe=59413864"));
        } else if (intValue == 1) {
            imageList.add(new ImageDao(1, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/14021701_1169485746446022_8096981577666880213_n.jpg?oh=b6c29acdbe6a4b9dd3b36f6eac506b40&oe=59289DEF"));
            imageList.add(new ImageDao(2, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/17022088_1352715054789756_7920857725060339481_n.jpg?oh=1589c81d310c1990a5f55a0b49c4c97b&oe=592B693F"));
            imageList.add(new ImageDao(3, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/16864911_1352714998123095_5844164069453441618_n.jpg?oh=5303ffaf3c4d660a40f7608828d97375&oe=593AFA74"));
            imageList.add(new ImageDao(4, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/17021968_1352714958123099_8079644190421821517_n.jpg?oh=27b2e9009f2039c56d8b604ced339ddb&oe=592720B2"));
            imageList.add(new ImageDao(5, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/16864277_1352714948123100_9110371764417772096_n.jpg?oh=1c7530c51c921116468cd45076a030f5&oe=59276844"));

        } else if (intValue == 2) {
            imageList.add(new ImageDao(1, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/13606871_1581968302099843_2099360564028036367_n.jpg?oh=91eda0d211c99b41d01c57f7d9b31f37&oe=593BE04C"));
            imageList.add(new ImageDao(2, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/14632991_1623639727932700_5857092354666970816_n.jpg?oh=599992aa838759bcc4b997720c2657e9&oe=59395401"));
            imageList.add(new ImageDao(3, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/15349760_1637265479903458_165747158208901418_n.jpg?oh=527d6b182f417b72780b20ec2f17b34f&oe=592CB0DC"));
        } else if (intValue == 3) {
            imageList.add(new ImageDao(1, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/15872017_1291649610855323_432103020786255615_n.jpg?oh=47e373cb9e1b8527fbeab0caca379379&oe=59301499"));
            imageList.add(new ImageDao(2, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/17098200_1355937331093217_3008700545127736162_n.jpg?oh=fe1d7f810371c427df609ade9087fdf2&oe=5926D9DC"));
            imageList.add(new ImageDao(3, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/17022164_1355935897760027_8348313892456790686_n.jpg?oh=0fc7b166cc05e11124c0348bf9f86c57&oe=59688A91"));
            imageList.add(new ImageDao(4, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/17155369_1355935507760066_8128219332411331914_n.jpg?oh=b10315ff52de3778c3c4a6aa0bfb3445&oe=59267311"));
            imageList.add(new ImageDao(5, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/17021634_1355559887797628_641313156982871123_n.jpg?oh=826d9e5d70d9618db08d12597d23dff4&oe=592BD692"));
            imageList.add(new ImageDao(6, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/17098145_1353519661334984_4370876363167229448_n.jpg?oh=b86ac2fc22fdf49fb36760d528d04b7d&oe=5927847A"));
            imageList.add(new ImageDao(7, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/17022482_1353518934668390_2897703949268843040_n.jpg?oh=0e2f7fb7a918a6b4eb53a4aefe608e5d&oe=5969CAC4"));
            imageList.add(new ImageDao(8, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/16938979_1353518168001800_4561419305629034027_n.jpg?oh=fb7373ef10ed434b7cb8650b4ade1a6b&oe=596C20E2"));
        } else if (intValue == 4) {
            imageList.add(new ImageDao(1, "https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/12717180_843418215767113_5605301514423727807_n.png?oh=b054cda8a0738ba702997198a6f1c7a8&oe=5930DD17"));
            imageList.add(new ImageDao(2, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/12961726_927954563984076_8501073290017362718_n.jpg?oh=aa34741e161ebd238cfaec79b5bf4244&oe=59335218"));
            imageList.add(new ImageDao(3, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-9/1510378_1720705991483740_2219541213949096995_n.jpg?oh=78078b2a41e20090b68683f69270c67d&oe=593514D6"));
            imageList.add(new ImageDao(4, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-0/p480x480/13240697_805385822898976_3769756852245455272_n.jpg?oh=bba980c514eed8e5b34b333a3726e024&oe=596A9814"));
            imageList.add(new ImageDao(5, "https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.0-0/p480x480/13082746_612945572197559_6789334437614933991_n.jpg?oh=32d2a1a7f48ef0543c0fbb83baa9f9d8&oe=5933A249"));
        }

        return imageList;
    }


    private View.OnClickListener OnClickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
