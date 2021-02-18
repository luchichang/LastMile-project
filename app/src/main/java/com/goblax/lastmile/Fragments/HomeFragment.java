package com.goblax.lastmile.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goblax.lastmile.Adapters.Admin_Adapter;
import com.goblax.lastmile.Adapters.Details_adapter;
import com.goblax.lastmile.R;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView, adminrecycler;
    LinearLayoutManager linearLayoutManager;
    TextView title;
    private ArrayList<HashMap<String, String>> hashMapArrayList = new ArrayList<>();
    Details_adapter details_adapter;
    Admin_Adapter admin_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        initialize(view);

//        if (SharePref.getUserInfo(getActivity()).equals("admin")) {
//            admin_adapter = new Admin_Adapter(getActivity(), hashMapArrayList);
//            adminrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//            adminrecycler.setAdapter(admin_adapter);
//            recyclerView.setVisibility(View.GONE);
//            title.setVisibility(View.GONE);
//            adminrecycler.setVisibility(View.VISIBLE);
//        } else {
        details_adapter = new Details_adapter(getActivity(), hashMapArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(details_adapter);
        recyclerView.setVisibility(View.VISIBLE);
        adminrecycler.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
//        }

        hashMapArrayList.clear();

        for (int i = 0; i < 10; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("store_name", "Abc Stores");
            hashMapArrayList.add(hashMap);
        }


        return view;


    }

    private void initialize(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.details_recycler);
        adminrecycler = (RecyclerView) view.findViewById(R.id.admin_recycler);
        title = view.findViewById(R.id.all_text);

    }
}
