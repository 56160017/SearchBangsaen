package com.buu.se.searchbangsaen.restaurant_categories.adapter;

import android.content.Context;
import android.content.Intent;
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


    public CardRestaurantSearchAdapter(Context context, List<RestaurantDao> restaurantlist) {
        this.mContext = context;
        this.restaurantList = restaurantlist;
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
        holder.tvDistance.setText(restaurantList.get(position).getDistance() + " กม.");
      //  holder.llDetail.setOnClickListener(OnItemClickListener);
        Calendar c = Calendar.getInstance();
        cHour = c.get(Calendar.HOUR);
        cMinute = c.get(Calendar.MINUTE);
        cSecond = c.get(Calendar.SECOND);
        if(restaurantList.get(position).getStatus()) {
            holder.ivStatus.setImageResource(R.drawable.ic_status_open);
        }else{
            holder.ivStatus.setImageResource(R.drawable.ic_status_closed);
        }
        if (position == 0) {
            Picasso.with(mContext).load("https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/403484_156009281197295_536660032_n.jpg?oh=a201d4b0399a80cc7535db8f50349334&oe=593FD711").into(holder.ivFood);
            holder.llDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mContext, DetailRestaurantActivity.class);
                    i.putExtra("key", position);
                    i.putExtra("data",restaurantList.get(position));
                    mContext.startActivity(i);
                }
            });
        } else if (position == 1) {
            Picasso.with(mContext).load("https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/14021701_1169485746446022_8096981577666880213_n.jpg?oh=b6c29acdbe6a4b9dd3b36f6eac506b40&oe=59289DEF").into(holder.ivFood);

            holder.llDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mContext, DetailRestaurantActivity.class);
                    i.putExtra("key",position);
                    i.putExtra("data",restaurantList.get(position));
                    mContext.startActivity(i);
                }
            });

        } else if (position == 2) {
            Picasso.with(mContext).load("https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/13606871_1581968302099843_2099360564028036367_n.jpg?oh=91eda0d211c99b41d01c57f7d9b31f37&oe=593BE04C").into(holder.ivFood);

            holder.llDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mContext, DetailRestaurantActivity.class);
                    i.putExtra("key",position);
                    i.putExtra("data",restaurantList.get(position));
                    mContext.startActivity(i);
                }
            });
        } else if (position == 3) {
            Picasso.with(mContext).load("https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/15872017_1291649610855323_432103020786255615_n.jpg?oh=47e373cb9e1b8527fbeab0caca379379&oe=59301499").into(holder.ivFood);

            holder.llDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mContext, DetailRestaurantActivity.class);
                    i.putExtra("key",position);
                    i.putExtra("data",restaurantList.get(position));
                    mContext.startActivity(i);
                }
            });
        } else if (position == 4) {
            Picasso.with(mContext).load("https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-9/12717180_843418215767113_5605301514423727807_n.png?oh=b054cda8a0738ba702997198a6f1c7a8&oe=5930DD17").into(holder.ivFood);
            holder.ivStatus.setImageResource(R.drawable.ic_status_closed);
            holder.llDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mContext, DetailRestaurantActivity.class);
                    i.putExtra("key",position);
                    i.putExtra("data",restaurantList.get(position));
                    mContext.startActivity(i);
                }
            });
        }

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
