package com.buu.se.searchbangsaen.restaurant_categories.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.restaurant_categories.activity.DetailRestaurantActivity;
import com.buu.se.searchbangsaen.restaurant_categories.dao.RestaurantDao;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CardRestaurantSearchAdapter extends RecyclerView.Adapter<CardRestaurantSearchAdapter.ViewHolder> {

    private Context mContext;
    private List<RestaurantDao> restaurantList;
    private int cHour;
    private int cMinute;
    private int cSecond;
    private StorageReference mStorage;

    public CardRestaurantSearchAdapter(Context context, List<RestaurantDao> restaurantlist) {
        this.mContext = context;
        this.restaurantList = restaurantlist;
        mStorage = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_card_restaurant, parent, false);
        ViewHolder holder = new CardRestaurantSearchAdapter.ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
      /* holder.tvName.setText(" " + restaurantList.get(position).getName());
        holder.tvDay.setText(restaurantList.get(position).getDay());
        Picasso.with(mContext).load("https://s3-ap-southeast-1.amazonaws.com/photo.wongnai.com/photos/2015/10/12/a6d237fa6fb94619bf5386fa0e41cdc7.jpg").into(holder.ivFood);
*/

        if(restaurantList.get(position).getName().trim().length() > 13){
            holder.tvName.setText(" " + restaurantList.get(position).getName().subSequence(0,12) + "..");
        }else{
            holder.tvName.setText(" " + restaurantList.get(position).getName());
        }

        holder.tvDay.setText(restaurantList.get(position).getDay());
        holder.tvOpen.setText("เปิด " + restaurantList.get(position).getOpen() + " น.");
        holder.tvClose.setText("ปิด  " + restaurantList.get(position).getClose() + " น.");
        holder.tvContact.setText("ติดต่อ " + restaurantList.get(position).getContact());
        holder.tvDistance.setText(restaurantList.get(position).getDistance() + "");
      //  holder.llDetail.setOnClickListener(OnItemClickListener);
        Calendar c = Calendar.getInstance();
        cHour = c.get(Calendar.HOUR);
        cMinute = c.get(Calendar.MINUTE);
        cSecond = c.get(Calendar.SECOND);
        if(restaurantList.get(position).getStatus()) {
            holder.ivStatus.setImageResource(R.drawable.ic_status_open);
            Log.d("ic_status: ", "ic_status_open");
        }else{
            holder.ivStatus.setImageResource(R.drawable.ic_status_closed);
            Log.d("ic_status: ", "ic_status_closed");
        }

        StorageReference filepath = mStorage.child("restaurant").child("" + restaurantList.get(position).getmUri().get(0));
        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(mContext).load(uri.toString()).into(holder.ivFood);
            }
        });


        holder.llDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, DetailRestaurantActivity.class);
                i.putExtra("key", position);
                i.putExtra("data", restaurantList.get(position));
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivFood) ImageView ivFood;
        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvDay) TextView tvDay;
        @BindView(R.id.tvOpen) TextView tvOpen;
        @BindView(R.id.tvClose) TextView tvClose;
        @BindView(R.id.tvContact) TextView tvContact;
        @BindView(R.id.iv_status) ImageView ivStatus;
        @BindView(R.id.tvDistance) TextView tvDistance;
        @BindView(R.id.llDetail) LinearLayout llDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
