package com.buu.se.searchbangsaen.editcategories.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.editcategories.fragment.EditHotelFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by landmark on 4/10/2017 AD.
 */

public class EditPageShopInAccountAdapter extends RecyclerView.Adapter<EditPageShopInAccountAdapter.ViewHolder> {

    private Context mContext;
    private EditHotelFragment editHotelFragment;

    public EditPageShopInAccountAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item_shop_in_account, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNameShopInEditPage.setText("ร้านค้า");
        //       Toast.makeText(mContext, "sss", Toast.LENGTH_LONG).show();

        holder.tvNameShopInEditPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "jjjjjjj", Toast.LENGTH_SHORT).show();
//                editHotelFragment = EditHotelFragment.newInstance();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.content_frame, editHotelFragment)
//                        .commit();
//
//                Fragment fragment = new EditHotelFragment();
//                FragmentManager fragmentManager = EditPageActivity().getSupportFragmentManager();


                FragmentManager manager = ((Activity) mContext).getFragmentManager();
                // android.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();

//      manager.beginTransaction().replace(R.id.container_frame, new EditHotelFragment.newInstance()).commit();          //      manager.beginTransaction().replace(R.id.container_frame, new EditHotelFragment.newInstance()).commit();








                // FragmentTransaction manager2 = ((Activity)mContext).getFragmentManager().beginTransaction().replace(R.id.container_frame, editHotelFragment);
                //  fragmentTransaction.replace(R.id.container_frame, editHotelFragment).commit();

                //Intent i = new Intent(mContext, EditHotelFragment.class);


                // Intent i = new Intent(mContext, EditPageActivity.class);
                //mContext.startActivity(i);
            }
        });

        holder.imgButtonShopInEditPicturePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "ooooo", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_name_shop_in_edit_page) TextView tvNameShopInEditPage;
        @BindView(R.id.img_button_shop_in_edit_picture_page) ImageButton imgButtonShopInEditPicturePage;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View view) {
            //  Toast.makeText(mContext, "sss", Toast.LENGTH_LONG).show();
        }
    }
}
