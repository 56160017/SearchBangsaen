package com.buu.se.searchbangsaen.add_categories.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.buu.se.searchbangsaen.add_categories.fragment.AddBenefitsResFragment;
import com.buu.se.searchbangsaen.add_categories.fragment.AddDetailResFragment;
import com.buu.se.searchbangsaen.add_categories.fragment.AddLatLngResFragment;

/**
 * Created by Dell on 10/04/2560.
 */
public class AddRestaurantAdapter extends FragmentStatePagerAdapter {

    private SparseArray<Fragment> AddResFragments = new SparseArray<Fragment>();

    public AddRestaurantAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment mFragment;
        if (0 == position) {
            mFragment = AddDetailResFragment.newInstance();
            //  mFragment = MainLoginFragment.newInstance();
        } else if (1 == position) {
                // mFragment = RegisterEmailFragment.newInstance();
                mFragment = AddLatLngResFragment.newInstance();
            }else{
            mFragment = AddBenefitsResFragment.newInstance();
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
        AddResFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        AddResFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getResFragment(int position) {
        return AddResFragments.get(position);
    }
}
