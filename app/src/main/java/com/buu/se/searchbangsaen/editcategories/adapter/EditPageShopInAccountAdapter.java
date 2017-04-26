package com.buu.se.searchbangsaen.editcategories.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.dao.AddRestaurantDao;
import com.buu.se.searchbangsaen.editcategories.fragment.EditHotelFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by landmark on 4/10/2017 AD.
 */

public class EditPageShopInAccountAdapter extends RecyclerView.Adapter<EditPageShopInAccountAdapter.ViewHolder> {

    private Context mContext;
    private EditHotelFragment editHotelFragment;
    private ArrayList<AddRestaurantDao> mAddRestaurantDao;

    private onEditShopSuccessClickNextListener mCallBack;

    public interface onEditShopSuccessClickNextListener {
        void onSuccessToEditClick(AddRestaurantDao addRestaurantDao);
    }
    public EditPageShopInAccountAdapter(Context mContext, ArrayList<AddRestaurantDao> mAddRestaurantDao) {
        this.mContext = mContext;
        this.mAddRestaurantDao = mAddRestaurantDao;
        mCallBack = (onEditShopSuccessClickNextListener) mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item_shop_in_account, parent, false);
        ViewHolder holder = new ViewHolder(v);
        Log.d( "sizeRes: ",""+mAddRestaurantDao.size());
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvNameShopInEditPage.setText(mAddRestaurantDao.get(position).getNameRestaurant());
        holder.tvNameShopInEditPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onSuccessToEditClick(mAddRestaurantDao.get(position));
            }
        });

        holder.imgButtonShopInEditPicturePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "ooooo", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAddRestaurantDao.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_name_shop_in_edit_page) TextView tvNameShopInEditPage;
        @BindView(R.id.img_button_shop_in_edit_picture_page) ImageButton imgButtonShopInEditPicturePage;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View view) {
        }
    }
}

