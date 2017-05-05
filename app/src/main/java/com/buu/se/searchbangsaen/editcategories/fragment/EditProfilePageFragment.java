package com.buu.se.searchbangsaen.editcategories.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.buu.se.searchbangsaen.R;
import com.buu.se.searchbangsaen.utils.CircleTransform;
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

import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfilePageFragment extends Fragment implements ScreenShotable {

    @BindView(R.id.btn_add_profile)
    Button btnAddProfile;
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_sure_name)
    EditText etSureName;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_email)
    EditText etEmail;
    private Context mContext;
    Unbinder unbinder;
    private View containerView;
    private StorageReference mStorage;
    public static final int REQUEST_GALLERY = 1;
    private Bitmap bitmap;
    private String name;

    public static EditProfilePageFragment newInstance() {
        EditProfilePageFragment editProfilePageFragment = new EditProfilePageFragment();
        return editProfilePageFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);


        mStorage = FirebaseStorage.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            initProfile(user);
            initProfileName(user);
          //  initData(user);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile_page, container, false);
        unbinder = ButterKnife.bind(this, view);

        btnAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent
                        , "Select Picture"), REQUEST_GALLERY);
            }
        });
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

    private void initProfileName(FirebaseUser user) {
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("users").child(user.getUid()).child("detail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // name = "" + dataSnapshot.child("name").getValue();
                etName.setText("" + dataSnapshot.child("name").getValue());
                etSureName.setText("" + dataSnapshot.child("surname").getValue());
                etTel.setText("" + dataSnapshot.child("phone").getValue());
                etEmail.setText("" + dataSnapshot.child("email").getValue());
//
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                ivProfile.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

