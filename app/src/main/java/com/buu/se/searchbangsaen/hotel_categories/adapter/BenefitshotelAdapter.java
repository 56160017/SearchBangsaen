package com.buu.se.searchbangsaen.hotel_categories.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.hotel_categories.activity.DetailHotelActivity;
import com.buu.se.searchbangsaen.hotel_categories.dao.BenefitsHotelDao;

/**
 * Created by Dell on 15/04/2560.
 */
public class BenefitshotelAdapter extends BaseAdapter {

    private BenefitsHotelDao mBenefitsHotelDao;
    private Context mContext;
    LayoutInflater inflater;
    public BenefitshotelAdapter(Context context, BenefitsHotelDao benefitsHotelDao) {
        mContext = context;
        mBenefitsHotelDao = benefitsHotelDao;
        inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 5;
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
            if (mBenefitsHotelDao.isChk_parking()) {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("มีที่จอดรถ");

                }
            }else{
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("มีที่จอดรถ");
                    iv.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_false));
                }
            }
            if (mBenefitsHotelDao.isChk_internat()) {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("internet");

                }
            }else {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("internet");
                    iv.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_false));
                }
            }
            if (mBenefitsHotelDao.isChk_dry()) {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("ร้านซักรีด");

                }
            }else {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("ร้านซักรีด");
                    iv.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_false));
                }
            }
            if (mBenefitsHotelDao.isChk_shop()) {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("ร้านสะดวกซื้อ");

                }
            }else {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("ร้านสะดวกซื้อ");
                    iv.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_false));
                }
            }
            if (mBenefitsHotelDao.isChk_taxi()) {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("บริการ Taxi");
                }
            }else {
                setText++;
                if (setText == position + 1) {
                    tvBenef.setText("บริการ Taxi");
                    iv.setImageDrawable(convertView.getResources().getDrawable(R.drawable.ic_false));
                }
            }

        }
        return convertView;
    }
}
