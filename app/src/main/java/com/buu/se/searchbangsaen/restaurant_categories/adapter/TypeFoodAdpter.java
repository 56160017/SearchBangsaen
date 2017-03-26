package com.buu.se.searchbangsaen.restaurant_categories.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;

import java.util.List;

/**
 * Created by Dell on 06/03/2560.
 */

public class TypeFoodAdpter extends BaseAdapter {
    private List<String> tfDao;
    private Context mContext;
    LayoutInflater inflater;

    public TypeFoodAdpter(Context context, List<String> tfDao) {
        mContext = context;
        this.tfDao = tfDao;
        inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
       return tfDao.size();

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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.typefood_layout, null);
            TextView tvtf = (TextView) convertView.findViewById(R.id.tv_type_food);
            tvtf.setText(tfDao.get(position));
        }
        return convertView;
    }

}
