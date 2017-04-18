package com.buu.se.searchbangsaen.add_categories.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.buu.se.searchbangsaen.add_categories.fragment.AddPictureHotelFragment;
import com.buu.se.searchbangsaen.add_categories.fragment.AddDetailHotelFragment;
import com.buu.se.searchbangsaen.add_categories.fragment.AddLatLngHotelFragment;

/**
 * Created by Dell on 14/04/2560.
 */

public class AddHotelAdapter extends FragmentStatePagerAdapter {
    private SparseArray<Fragment> AddHotelFragments = new SparseArray<Fragment>();
    public AddHotelAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment mFragment;
        if (0 == position) {
            mFragment = AddDetailHotelFragment.newInstance();
            //  mFragment = MainLoginFragment.newInstance();
        } else if (1 == position) {
            // mFragment = RegisterEmailFragment.newInstance();
            mFragment = AddLatLngHotelFragment.newInstance();
        }else{
            mFragment = AddPictureHotelFragment.newInstance();
        }
        return mFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        AddHotelFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        AddHotelFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getHotelFragment(int position) {
        return AddHotelFragments.get(position);
    }
}
