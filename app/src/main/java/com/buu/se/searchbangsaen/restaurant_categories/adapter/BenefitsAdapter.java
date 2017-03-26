package com.buu.se.searchbangsaen.restaurant_categories.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.restaurant_categories.dao.BenefitsDao;

/**
 * Created by Dell on 03/03/2560.
 */
public class BenefitsAdapter extends BaseAdapter {
    private BenefitsDao mBenefits;
    private Context mContext;
    LayoutInflater inflater;

    public BenefitsAdapter(Context context, BenefitsDao BenefitsDao) {
        mContext = context;
        mBenefits = BenefitsDao;
        inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mBenefits.getSize();
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
            int setText = 0;

                if (mBenefits.isParking()) {
                    setText++;
                    if (setText == position + 1) {
                        tvBenef.setText("มีที่จอดรถ");

                    }
                }
                if (mBenefits.isCreditCards()) {
                    setText++;
                    if (setText == position + 1) {
                        tvBenef.setText("ใช้เคตดิตการ์ด");

                    }
                }
                if (mBenefits.isAlcohol()) {
                    setText++;
                    if (setText == position + 1) {
                        tvBenef.setText("มีแอลกอฮอ");

                    }
                }
                if (mBenefits.isReservation()) {
                    setText++;
                    if (setText == position + 1) {
                        tvBenef.setText("จองล่วงหน้า");

                    }
                }
                if (mBenefits.isLiveMusic()) {
                    setText++;
                    if (setText == position + 1) {
                        tvBenef.setText("มีดนตรีสด");
                    }
                }
        }

//        Button button = (Button) convertView.findViewById(R.id.grid_item);
//        button.setText(items[position]);

        return convertView;


    }
}
