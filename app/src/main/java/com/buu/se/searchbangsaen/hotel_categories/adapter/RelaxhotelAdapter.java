package com.buu.se.searchbangsaen.hotel_categories.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.hotel_categories.dao.BenefitsHotelDao;
import com.buu.se.searchbangsaen.hotel_categories.dao.RelaxsDao;

/**
 * Created by Dell on 15/04/2560.
 */
public class RelaxhotelAdapter extends BaseAdapter {

    private RelaxsDao relaxDao;
    private Context mContext;
    LayoutInflater inflater;
    public RelaxhotelAdapter(Context context, RelaxsDao relaxsDao) {
        mContext = context;
        relaxDao = relaxsDao;
        inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 4;
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
            convertView = inflater.inflate(R.layout.benefits_layout, null);
            TextView tvBenef = (TextView) convertView.findViewById(R.id.tv_benef);
            ImageView iv = (ImageView) convertView.findViewById(R.id.iv_st);
            int setText = 0;
            if (relaxDao.isFitness()) {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("ฟิตเนส");

                }
            }else{
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("ฟิตเนส");
                    iv.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_false));
                }
            }
            if (relaxDao.isGolf()) {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("สนามกอล์ฟ");
                }
            }else {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("สนามกอล์ฟ");
                    iv.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_false));
                }
            }
            if (relaxDao.isPlayground()) {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("สนามเด็กเล่น");

                }
            }else {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("สนามเด็กเล่น");
                    iv.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_false));
                }
            }
            if (relaxDao.isSwim()) {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("สระว่ายน้ำ");

                }
            }else {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("สระว่ายน้ำ");
                    iv.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_false));
                }
            }

        }
        return convertView;
    }
}
