package com.buu.se.searchbangsaen.searchcategories;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dell on 09/01/2560.
 */

public class RecyclerRestaurantSearch extends RecyclerView.Adapter<RecyclerRestaurantSearch.ViewHolder> {
    private Context mContext;
    private List<RestaurantDao> restaurantList;

    public RecyclerRestaurantSearch(Context context, List<RestaurantDao> restaurantlist) {
        this.mContext = context;
        this.restaurantList = restaurantlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext)
                //           .inflate(R.layout.recycleview_list_item, parent, false);
                .inflate(R.layout.list_item_recycle_restaurant, parent, false);
        ViewHolder holder = new ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0) {
            holder.tvName.setText(restaurantList.get(position).getName());
            holder.tvDay.setText(restaurantList.get(position).getDay());
            holder.tvOpen.setText("เปิด " + restaurantList.get(position).getOpen() + " น.");
            holder.tvClose.setText("ปิด  " + restaurantList.get(position).getClose() + " น.");
            holder.tvContact.setText("ติดต่อ " + restaurantList.get(position).getContact());
            holder.llDetail.setOnClickListener(OnItemClickListener);
        } else if (position == 1) {
            holder.tvName.setText(restaurantList.get(position).getName());
            holder.tvDay.setText(restaurantList.get(position).getDay());
            holder.tvOpen.setText("เปิด " + restaurantList.get(position).getOpen() + " น.");
            holder.tvClose.setText("ปิด  " + restaurantList.get(position).getClose() + " น.");
            holder.tvContact.setText("ติดต่อ " + restaurantList.get(position).getContact());
        } else if (position == 2) {
            holder.tvName.setText(restaurantList.get(position).getName());
            holder.tvDay.setText(restaurantList.get(position).getDay());
            holder.tvOpen.setText("เปิด " + restaurantList.get(position).getOpen() + " น.");
            holder.tvClose.setText("ปิด  " + restaurantList.get(position).getClose() + " น.");
            holder.tvContact.setText("ติดต่อ " + restaurantList.get(position).getContact());
        } else if (position == 3) {
            holder.tvName.setText(restaurantList.get(position).getName());
            holder.tvDay.setText(restaurantList.get(position).getDay());
            holder.tvOpen.setText("เปิด " + restaurantList.get(position).getOpen() + " น.");
            holder.tvClose.setText("ปิด  " + restaurantList.get(position).getClose() + " น.");
            holder.tvContact.setText("ติดต่อ " + restaurantList.get(position).getContact());
        } else if (position == 4) {
            holder.tvName.setText(restaurantList.get(position).getName());
            holder.tvDay.setText(restaurantList.get(position).getDay());
            holder.tvOpen.setText("เปิด " + restaurantList.get(position).getOpen() + " น.");
            holder.tvClose.setText("ปิด  " + restaurantList.get(position).getClose() + " น.");
            holder.tvContact.setText("ติดต่อ " + restaurantList.get(position).getContact());
        } else if (position == 5) {
            holder.tvName.setText(restaurantList.get(position).getName());
            holder.tvDay.setText(restaurantList.get(position).getDay());
            holder.tvOpen.setText("เปิด " + restaurantList.get(position).getOpen() + " น.");
            holder.tvClose.setText("ปิด  " + restaurantList.get(position).getClose() + " น.");
            holder.tvContact.setText("ติดต่อ " + restaurantList.get(position).getContact());
        }


    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.imgStatus) ImageView imgStatus;
        @BindView(R.id.tvDay) TextView tvDay;
        @BindView(R.id.tvOpen) TextView tvOpen;
        @BindView(R.id.tvClose) TextView tvClose;
        @BindView(R.id.tvContact) TextView tvContact;
        @BindView(R.id.tvStatus) TextView tvStatus;
        @BindView(R.id.llDetail) LinearLayout llDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private View.OnClickListener OnItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
