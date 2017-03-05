package com.buu.se.searchbangsaen.searchcategories.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.searchcategories.dao.ImageDao;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dell on 27/02/2560.
 */
public class RecyclerImageAdapter extends RecyclerView.Adapter<RecyclerImageAdapter.ViewHolder> {
    private Context mContext;
    private List<ImageDao> imageList;

    public RecyclerImageAdapter(Context context, List<ImageDao> imageList) {
        this.mContext = context;
        this.imageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_image, parent, false);
        ViewHolder holder = new RecyclerImageAdapter.ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerImageAdapter.ViewHolder holder, int position) {
        Picasso.with(mContext).load(imageList.get(position).getUrl()).into(holder.ivShow);
        final ImageDao item = imageList.get(position);
        holder.ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentJump(item);

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
