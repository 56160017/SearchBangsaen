package com.buu.se.searchbangsaen.add_categories.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.buu.se.searchbangsaen.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 30/04/2560.
 */

public class AddImageAdapter extends BaseAdapter {
    private Context mContext;
    LayoutInflater inflater;
    private List<Uri> mUri;
    private Uri uri ;
    public AddImageAdapter(Context context) {
        this.uri = uri;
        mUri = new ArrayList<>();
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return mUri.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.image_layout, null);
            final ImageView ivAdd = (ImageView) convertView.findViewById(R.id.iv_image);
            ivAdd.setImageURI(mUri.get(position));
            ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  mUri.remove(position);
                }
            });
        }
        return convertView;
    }

    public void addUri(Uri uri) {
        mUri.add(uri);
    }

    public List<Uri> getAllUri() {
        return mUri;
    }
}