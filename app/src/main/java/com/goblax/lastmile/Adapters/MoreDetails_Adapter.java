package com.goblax.lastmile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.goblax.lastmile.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MoreDetails_Adapter extends RecyclerView.Adapter<MoreDetails_Adapter.MyViewHolder> {
    private Context context;
    private ArrayList<HashMap<String, String>> boardSpaceitems = new ArrayList<>();

    public MoreDetails_Adapter(Context context, ArrayList<HashMap<String, String>> boardSpaceitems) {
        this.context = context;
        this.boardSpaceitems = boardSpaceitems;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView need_name, need_edu,check_details;

        public MyViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public MoreDetails_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.moredatails_content, parent, false);
        return new MoreDetails_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoreDetails_Adapter.MyViewHolder holder, final int position) {
    }


    @Override
    public int getItemCount() {
        return boardSpaceitems.size();
    }
}
