package com.buu.se.searchbangsaen.auth.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.buu.se.searchbangsaen.auth.fragment.LoginFragment;
import com.buu.se.searchbangsaen.auth.fragment.MainRegisterFragment;

/**
 * Created by Dell on 24/03/2560.
 */
public class AuthMenuAdapter extends FragmentStatePagerAdapter {

    private SparseArray<Fragment> AuthFragments = new SparseArray<Fragment>();

    public AuthMenuAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment mFragment;
        if (0 == position){
            mFragment = LoginFragment.newInstance();
          //  mFragment = MainLoginFragment.newInstance();
        } else if(1 == position) {
           // mFragment = RegisterEmailFragment.newInstance();
            mFragment = MainRegisterFragment.newInstance();
        } else {
            mFragment = MainRegisterFragment.newInstance();
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
        AuthFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        AuthFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getAuthFragment(int position) {
        return AuthFragments.get(position);
    }
}
