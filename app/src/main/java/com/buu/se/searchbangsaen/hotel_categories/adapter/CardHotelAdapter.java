package com.buu.se.searchbangsaen.hotel_categories.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.hotel_categories.activity.DetailHotelActivity;
import com.buu.se.searchbangsaen.hotel_categories.dao.HotelDao;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dell on 10/03/2560.
 */
public class CardHotelAdapter extends RecyclerView.Adapter<CardHotelAdapter.ViewHolder> {
    private Context mContext;
    private List<HotelDao> hotelList;

    public CardHotelAdapter(Context mContext, List<HotelDao> restaurantList) {
        this.mContext = mContext;
        this.hotelList = restaurantList;
    }

    @Override
    public CardHotelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_card_hotel, parent, false);
        CardHotelAdapter.ViewHolder holder = new CardHotelAdapter.ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");

        if(hotelList.get(position).getName().trim().length() > 20){
            holder.tvName.setText(" " + hotelList.get(position).getName().trim().subSequence(0,18) + "..");
        }else{
            holder.tvName.setText(" " + hotelList.get(position).getName());
        }

        holder.tvRoom.setText("เหลือ " + hotelList.get(position).getEmpty_room() + " ห้องสุดท้าย");
        holder.tvContact.setText("ติดต่อ " + hotelList.get(position).getPhone());
        holder.tvPriceTitleF.setText("THB " + formatter.format(Integer.parseInt(hotelList.get(position).getPrice_f())));
        holder.tvPriceTitleF.setPaintFlags(holder.tvPriceTitleF.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvPriceTitleT.setText("THB " + formatter.format(Integer.parseInt(hotelList.get(position).getPrice_t())));

        holder.tvDistance.setText(hotelList.get(position).getDistance());

        //img
        if (position == 0) {
            Picasso.with(mContext).load("https://scontent.fbkk10-1.fna.fbcdn.net/v/t1.0-0/p206x206/1934604_10153418966847304_790610692157339225_n.jpg?oh=93bfb5e2f6160b981e4fda636b6211c2&oe=597312C5").into(holder.ivHotel);
            setOnclickDetailListener(holder, position);
        } else if (position == 1) {
            Picasso.with(mContext).load("http://www.saensukcity.com/images/hotel/heritage/heritage.jpg").into(holder.ivHotel);
            setOnclickDetailListener(holder, position);
        } else if (position == 2) {
            Picasso.with(mContext).load("http://www.saensukcity.com/images/hotel/thetideresort/bangsaen/thetideresort-03s.jpg").into(holder.ivHotel);
            setOnclickDetailListener(holder, position);
        }else{
            setOnclickDetailListener(holder, position);
        }
    }

    private void setOnclickDetailListener(ViewHolder holder, final int position) {
        holder.llDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailHotelActivity.class);
                i.putExtra("key", position);
                i.putExtra("data", hotelList.get(position));
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hotel) ImageView ivHotel;
        @BindView(R.id.tv_name) TextView tvName;
        @BindView(R.id.tv_room) TextView tvRoom;
        @BindView(R.id.tv_contact) TextView tvContact;
        @BindView(R.id.tv_price_title) TextView tvPriceTitle;
        @BindView(R.id.tv_price_title_f) TextView tvPriceTitleF;
        @BindView(R.id.tv_price_title_t) TextView tvPriceTitleT;
        @BindView(R.id.tvDistance) TextView tvDistance;
        @BindView(R.id.llDetail) LinearLayout llDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
