package com.buu.se.searchbangsaen.editcategories.fragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.editcategories.adapter.EditPageShopInAccountAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

//import android.support.v7.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDataPageFragment extends Fragment implements ScreenShotable {

    @BindView(R.id.container)
    LinearLayout container;
    Unbinder unbinder;
    @BindView(R.id.list_shop_in_account)
    RecyclerView listShopInAccount;
    //    @BindView(R.id.btn_submit) Button btnSubmit;
    //   private RecyclerView listShopInAccount;
    private View containerView;
    private Bitmap bitmap;
    private EditPageShopInAccountAdapter editadpter;
    private Context mContext;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
        editadpter = new EditPageShopInAccountAdapter(mContext);

        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listShopInAccount.setLayoutManager(llm);
        listShopInAccount.setAdapter(editadpter);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

}

