package com.goblax.lastmile.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.goblax.lastmile.BasicActivities.StoreDetailsActivity;
import com.goblax.lastmile.BasicActivities.StoreEventsDetailsActivity;
import com.goblax.lastmile.R;
import com.goblax.lastmile.Utils.SharePref;
import com.skydoves.powermenu.PowerMenu;

import java.util.ArrayList;
import java.util.HashMap;

public class Details_adapter extends RecyclerView.Adapter<Details_adapter.MyViewHolder> {
    private Context context;
    private ArrayList<HashMap<String, String>> faqcontents = new ArrayList<>();
    PowerMenu powerMenu;


    public Details_adapter(FragmentActivity activity, ArrayList<HashMap<String, String>> faqcontents) {
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
            more_icon = view.findViewById(R.id.more_con);
            adpater_con = view.findViewById(R.id.adpater_con);

        }
    }

    @Override
    public Details_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_adapter, parent, false);
        return new Details_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Details_adapter.MyViewHolder holder, final int position) {
        holder.title.setText(faqcontents.get(position).get("store_name"));
//
        holder.adpater_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharePref.getFromTab(context).equals("1")) {
                    context.startActivity(new Intent(context, StoreDetailsActivity.class));
                } else {
                    context.startActivity(new Intent(context, StoreEventsDetailsActivity.class));
                }

            }
        });

        holder.more_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" + "8098548757");
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                context.startActivity(i);
            }
        });


    }


    @Override
    public int getItemCount() {
        return faqcontents.size();
    }
}
