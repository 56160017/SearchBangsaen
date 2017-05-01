package com.buu.se.searchbangsaen.add_categories.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.dao.AddImageDao;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Dell on 26/04/2560.
 */
public class RecyclerAddImageAdapter extends RecyclerView.Adapter<RecyclerAddImageAdapter.ViewHolder> {

    private Context mContext;
    private List<AddImageDao> imageList;
    public static final int REQUEST_GALLERY = 1;
    private Uri uri;

    private onAddImageClickListener mImageCallBack;

    public interface onAddImageClickListener {
        void onSuccessAddImageClick();

    }

    public RecyclerAddImageAdapter(Context context, List<AddImageDao> imageList) {
        this.mContext = context;
        this.imageList = imageList;
      //  mImageCallBack = (onAddImageClickListener) context;
    }


    @Override
    public RecyclerAddImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_add_image, parent, false);
        ViewHolder holder = new RecyclerAddImageAdapter.ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerAddImageAdapter.ViewHolder holder, int position) {

        if (position == imageList.size()) {
            holder.ivShow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.id_add_image));
            holder.ivShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
      //              mImageCallBack.onSuccessAddImageClick();

                   /* Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image*//*");
                    ((Activity) mContext).startActivityForResult(Intent.createChooser(intent
                            , "Select Picture"), REQUEST_GALLERY);*/

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    ((Activity) mContext).startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);
                }
            });
        }else{
            Picasso.with(mContext).load(imageList.get(position).getUrl()).into(holder.ivShow);
        }

    }


   /* public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            uri = data.getData();
           *//* try {
                *//**//*bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                ivProfileAdd.setImageBitmap(bitmap);*//**//*

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            next = true;*//*
        }
    }*/

    @Override
    public int getItemCount() {
        return imageList.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivShow) ImageView ivShow;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
