package com.goblax.lastmile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.goblax.lastmile.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin_Adapter extends RecyclerView.Adapter<Admin_Adapter.MyViewHolder> {
    private Context context;
    private ArrayList<HashMap<String, String>> faqcontents = new ArrayList<>();


    public Admin_Adapter(FragmentActivity activity, ArrayList<HashMap<String, String>> faqcontents) {
        this.faqcontents = faqcontents;
        this.context = activity;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        RelativeLayout more_icon;
        CardView adpater_con;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.store_title);
//            more_icon = view.findViewById(R.id.more_con);
            adpater_con = view.findViewById(R.id.adpater_con);

        }
    }

    @Override
    public Admin_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_adapter, parent, false);
        return new Admin_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Admin_Adapter.MyViewHolder holder, final int position) {
        holder.title.setText(faqcontents.get(position).get("store_name"));

        holder.adpater_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(context, StoreDetailsActivity.class));
                Toast.makeText(context, "Feature to be launched soon!!", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return faqcontents.size();
    }
}
