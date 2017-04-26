package com.buu.se.searchbangsaen.editcategories.fragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.add_categories.dao.AddRestaurantDao;
import com.buu.se.searchbangsaen.editcategories.adapter.EditPageShopInAccountAdapter;
import com.buu.se.searchbangsaen.utils.CircleTransform;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

//import android.support.v7.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDataPageFragment extends Fragment implements ScreenShotable {

    @BindView(R.id.container)
    LinearLayout container;
    Unbinder unbinder;
    @BindView(R.id.list_shop_in_account) RecyclerView listShopInAccount;
    @BindView(R.id.iv_profile) CircleImageView ivProfile;
    @BindView(R.id.tv_value_shop) TextView tvValueShop;
    //    @BindView(R.id.btn_submit) Button btnSubmit;
    //   private RecyclerView listShopInAccount;
    private View containerView;
    private Bitmap bitmap;
    private EditPageShopInAccountAdapter editadpter;
    private Context mContext;
    private StorageReference mStorage;
    private AddRestaurantDao addRestaurantDao;
    private ArrayList<AddRestaurantDao> mAddRestaurantDao;

    public static EditDataPageFragment newInstance() {
        EditDataPageFragment editDataPageFragment = new EditDataPageFragment();
        return editDataPageFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_data_page, container, false);
        unbinder = ButterKnife.bind(this, view);

        //    listShopInAccount = (RecyclerView) view.findViewById(R.id.list_shop_in_account);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);


        mAddRestaurantDao = new ArrayList<>();


        mStorage = FirebaseStorage.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            initProfile(user);
            initData(user);
        }


    }


    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initProfile(FirebaseUser user) {
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("users").child(user.getUid()).child("detail").child("pic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StorageReference filepath = mStorage.child("profile").child("" + dataSnapshot.getValue());
                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.with(getActivity()).load(uri.toString()).transform(new CircleTransform())
                                .placeholder(getResources().getDrawable(R.drawable.user_profile)).into(ivProfile);
                    }
                });
                //
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initData(final FirebaseUser user) {


        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("restaurant").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //  Log.d("onDataChange: ", String.valueOf(dataSnapshot.getChildrenCount()));
                    for (DataSnapshot uuRes : dataSnapshot.getChildren()) {
                        addRestaurantDao = new AddRestaurantDao();
                        // Log.d("onDataChangeRes: ", "" + uuRes.getKey());
                        //   for (DataSnapshot uuid : uuRes.getChildren()) {
                        //       Log.d("onDataChangeID: ", "//" + uuRes.getKey());
                        if (("" + uuRes.child("uid").getValue()).matches(user.getUid())) {
                            Log.d("onDataChangekey: ", "true" + uuRes.getKey());
                            Log.d("onDataChangeUid: ", "true" + uuRes.child("uid").getValue());
                            Log.d("onDataChangeUid: ", "" + uuRes.child("name").getValue());
                            addRestaurantDao.setNameRestaurant("" + uuRes.child("name").getValue());
                            addRestaurantDao.setResPhone("" + uuRes.child("phone").getValue());
                            addRestaurantDao.setResOpen("" + uuRes.child("time-open").getValue());
                            addRestaurantDao.setResClose("" + uuRes.child("time-close").getValue());
                            addRestaurantDao.setResAddress("" + uuRes.child("address").getValue());

                            addRestaurantDao.setResDate("จันทร์-ศุกร์");

                            LatLng latLng = new LatLng(Double.parseDouble("" + uuRes.child("latitude").getValue()), Double.parseDouble("" + uuRes.child("longitude").getValue()));
                            addRestaurantDao.setResLatLng(latLng);

                            mAddRestaurantDao.add(addRestaurantDao);
                        }
                    }
                }
                Log.d("onsize: ", "" + mAddRestaurantDao.size());
                editadpter = new EditPageShopInAccountAdapter(mContext, mAddRestaurantDao);
                LinearLayoutManager llm = new LinearLayoutManager(mContext);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                listShopInAccount.setLayoutManager(llm);
                listShopInAccount.setAdapter(editadpter);
                tvValueShop.setText("" + mAddRestaurantDao.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}

