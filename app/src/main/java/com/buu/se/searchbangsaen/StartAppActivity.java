package com.buu.se.searchbangsaen;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.LayoutDirection;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class StartAppActivity extends AhoyOnboarderActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_start_app);
        AhoyOnboarderCard ahoyOnboarderCardFirst = new AhoyOnboarderCard("Search Bangsean", "Easy Sreach Easy Go!!", R.drawable.ic_coconut);
        ahoyOnboarderCardFirst.setTitleTextSize(30);
        ahoyOnboarderCardFirst.setDescriptionTextSize(25);
        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("คุณกำลังหาอะไร", "จะเที่ยวไหนก็หาเจอ \nจะไปไหนก็รู้ก่อนใคร", R.drawable.ic_spend);
        ahoyOnboarderCard1.setTitleTextSize(30);
        ahoyOnboarderCard1.setDescriptionTextSize(25);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("คุณจะไปยังไง", "มีแผนที่ในการนำทาง \nอยู่ตรงไหนก็ไปถูก", R.drawable.ic_map_of_road);
        ahoyOnboarderCard2.setTitleTextSize(30);
        ahoyOnboarderCard2.setDescriptionTextSize(25);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("เริ่มใช้งานกันเลย!!", "", R.drawable.ic_reward);
        ahoyOnboarderCard3.setTitleTextSize(30);
      //  ahoyOnboarderCard2.setDescriptionTextSize(25);

        ahoyOnboarderCardFirst.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        List<AhoyOnboarderCard> pages = new ArrayList<>();
        pages.add(ahoyOnboarderCardFirst);
        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
        }
       // ahoyOnboarderCardFirst.setIconLayoutParams(500,500,50,0,0,0);

        setFinishButtonTitle("เริ่มต้นใช้งาน");
        setFinishButtonDrawableStyle(getResources().getDrawable(R.drawable.btn_submit_first_page));
        showNavigationControls(false);

        //setGradientBackground();
       // setImageBackground(R.drawable.bg_landing);
          //  setImageBackground(R.drawable.bg_landing);

        List<Integer> colorList = new ArrayList<>();
        colorList.add(R.color.solid_frist);
        colorList.add(R.color.solid_one);
        colorList.add(R.color.solid_two);
        colorList.add(R.color.solid_frist);
        setColorBackground(colorList);


        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/DB_ThaiText/DB_ThaiText_Bold_X.ttf");

        setFont(face);
//        setInactiveIndicatorColor(R.color.grey_600);
//        setActiveIndicatorColor(R.color.white);
        setOnboardPages(pages);
    }

    @Override
    public void setFinishButtonDrawableStyle(Drawable res) {
        super.setFinishButtonDrawableStyle(res);

    }


    @Override
    public void onFinishButtonPressed() {
       // Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
