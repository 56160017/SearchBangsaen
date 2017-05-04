package com.buu.se.searchbangsaen.hotel_categories.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.restaurant_categories.activity.ShowImageActivity;
import com.buu.se.searchbangsaen.restaurant_categories.dao.ImageDao;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dell on 27/02/2560.
 */
public class RecyclerImageHotelAdapter extends RecyclerView.Adapter<RecyclerImageHotelAdapter.ViewHolder> {
    private final StorageReference mStorage;
    private Context mContext;
    private List<ImageDao> imageList;
    private String mTextTitle;

    public RecyclerImageHotelAdapter(Context context, List<ImageDao> imageList, String titlename) {
        this.mContext = context;
        this.imageList = imageList;
        this.mTextTitle = titlename;
        mStorage = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_image, parent, false);
        ViewHolder holder = new RecyclerImageHotelAdapter.ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerImageHotelAdapter.ViewHolder holder, final int position) {
        //Picasso.with(mContext).load(imageList.get(position).getUrl()).into(holder.ivShow);
        final ImageDao item = imageList.get(position);
        Log.d("Picasso",String.valueOf(imageList.get(position).getUrl()).trim());
        StorageReference filepath = mStorage.child("hotel").child(imageList.get(position).getUrl());
        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(final Uri uri) {
                Log.d("Picasso",uri.toString());
                Picasso.with(mContext).load(uri.toString()).into(holder.ivShow);
                holder.ivShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //  fragmentJump(item);
                        Intent i = new Intent(mContext, ShowImageActivity.class);
                        i.putExtra("tvname", mTextTitle);
                        i.putExtra("imageSrc", uri.toString());
                        mContext.startActivity(i, ActivityOptionsCompat.makeSceneTransitionAnimation(
                                (Activity) mContext, holder.ivShow, "shareView").toBundle());
                    }
                });
            }

        });



    }

    private void fragmentJump(ImageDao mItemSelected) {
       // mFragment = new Fragment2();
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivShow) ImageView ivShow;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
