package com.goblax.lastmile.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.goblax.lastmile.R;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreDeatilsAdapter extends RecyclerView.Adapter<StoreDeatilsAdapter.StoreDetailsViewHolder> {
    ArrayList<HashMap<String,String>> storeDetailsList=new ArrayList<>();
    Context context;



    public StoreDeatilsAdapter(Activity storeDetailsActivity, ArrayList<HashMap<String, String>> storeDetailsList) {
        this.storeDetailsList=storeDetailsList;
        this.context=storeDetailsActivity;
    }



    @NonNull
    @Override
    public StoreDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.store_deatils_adapter,parent,false);
        return new StoreDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreDetailsViewHolder holder, int position) {
        holder.details.setText(storeDetailsList.get(position).get("detail"));

    }

    @Override
    public int getItemCount() {
        return storeDetailsList.size();
    }

    public class StoreDetailsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView details;
        CheckBox checkBox;
        public StoreDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.profile_image);
            details=(TextView) itemView.findViewById(R.id.product_details);
            checkBox=(CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}
