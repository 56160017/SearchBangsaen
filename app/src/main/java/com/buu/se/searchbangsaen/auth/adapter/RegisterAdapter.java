package com.buu.se.searchbangsaen.auth.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.buu.se.searchbangsaen.auth.fragment.RegisterDetailFragment;
import com.buu.se.searchbangsaen.auth.fragment.RegisterEmailFragment;
import com.buu.se.searchbangsaen.auth.fragment.RegisterPictureFragment;

/**
 * Created by Dell on 25/03/2560.
 */

public class RegisterAdapter extends FragmentStatePagerAdapter {
    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
    public RegisterAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment mFragment;
        if (0 == position) {
            mFragment = RegisterEmailFragment.newInstance();
        }else if(1 == position){
            mFragment = RegisterDetailFragment.newInstance();
        }else{
            mFragment = RegisterPictureFragment.newInstance();
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
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
