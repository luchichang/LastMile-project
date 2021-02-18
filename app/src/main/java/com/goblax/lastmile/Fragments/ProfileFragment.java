package com.goblax.lastmile.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.goblax.lastmile.BasicActivities.Login_Activity;
import com.goblax.lastmile.R;


public class ProfileFragment extends Fragment {
    RelativeLayout logout,status,attendance,account;
    public ProfileFragment() {
    }



    public static ProfileFragment newInstance(int page, String title) {
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        //bundle.putInt("int",page);
        //bundle.putString("title",title);
        profileFragment.setArguments(bundle);
        return profileFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  page=getArguments().getInt("int",0);
        //title=getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_fragment, container, false);

        initialize(view);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialogue();
            }
        });




        return view;
    }

    private void initialize(View view) {
        logout = (RelativeLayout) view.findViewById(R.id.logout_layout);
        status = (RelativeLayout) view.findViewById(R.id.status_layout);
        attendance = (RelativeLayout) view.findViewById(R.id.attendance_lay);
        account = (RelativeLayout) view.findViewById(R.id.account_layout);
    }

    private void getDialogue() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_signout, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setView(view);

        RelativeLayout confirm = (RelativeLayout) view.findViewById(R.id.confirm);
        RelativeLayout cancel = (RelativeLayout) view.findViewById(R.id.decline);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Login_Activity.class));
                getActivity().finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
