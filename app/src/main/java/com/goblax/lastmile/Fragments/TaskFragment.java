package com.goblax.lastmile.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.goblax.lastmile.Adapters.Details_adapter;
import com.goblax.lastmile.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskFragment extends Fragment {
    RecyclerView active_recycler, completed_recycler;
    LinearLayoutManager linearLayoutManager;
    RelativeLayout completed_lay, active_lay;
    private ArrayList<HashMap<String, String>> active_list = new ArrayList<>();
    private ArrayList<HashMap<String, String>> completed_list = new ArrayList<>();
    Details_adapter details_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_fragment, container, false);
        active_list.clear();
        completed_list.clear();
        initialize(view);

        for (int i = 0; i < 5; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("store_name", "Abc Stores");
            active_list.add(hashMap);
        }

        for (int i = 0; i < 10; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("store_name", "XYZ Stores");
            completed_list.add(hashMap);
        }
//
//        active_lay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                completed_recycler.setVisibility(View.GONE);
//                active_recycler.setVisibility(View.VISIBLE);
//                //load to adpter
//                details_adapter = new Details_adapter(getActivity(), active_list);
//                active_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//                active_recycler.setAdapter(details_adapter);
//            }
//        });


        completed_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completed_recycler.setVisibility(View.VISIBLE);
                active_recycler.setVisibility(View.GONE);
                //load to adpter
                details_adapter = new Details_adapter(getActivity(), completed_list);
                completed_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                completed_recycler.setAdapter(details_adapter);
            }
        });

        details_adapter = new Details_adapter(getActivity(), active_list);
        active_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        active_recycler.setAdapter(details_adapter);
        completed_recycler.setVisibility(View.GONE);


        return view;


    }

    private void initialize(View view) {
        active_recycler = (RecyclerView) view.findViewById(R.id.active_task_recycler);
        completed_recycler = (RecyclerView) view.findViewById(R.id.complete_task_recycler);
        completed_lay = view.findViewById(R.id.task_layout);


    }
}
