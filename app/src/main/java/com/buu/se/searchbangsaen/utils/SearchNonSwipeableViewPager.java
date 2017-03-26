package com.buu.se.searchbangsaen.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Dell on 24/03/2560.
 */

public class SearchNonSwipeableViewPager extends ViewPager {
    private boolean isSwipeable;

    public SearchNonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        isSwipeable = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return isSwipeable ? super.onTouchEvent(event) : false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return isSwipeable ? super.onInterceptTouchEvent(event) : false;
    }

    public void setSwipeable(boolean swipeable) {
        isSwipeable = swipeable;
    }
}
