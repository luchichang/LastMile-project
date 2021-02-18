package com.goblax.lastmile.BasicActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goblax.lastmile.Adapters.StoreDeatilsAdapter;
import com.goblax.lastmile.R;
import com.skydoves.powermenu.PowerMenu;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreEventsDetailsActivity extends AppCompatActivity {
    private PowerMenu powerMenu;
    RelativeLayout cashCollect, share, back, map_layout, cal_layout, Complete_lay, more_con, Re_Attempt_lay, cancel_lay;
    RecyclerView productsRecycler;
    public static ImageView store_image;
    StoreDeatilsAdapter storeDeatilsAdapter;
    LinearLayoutManager linearLayoutManager;
    private Uri mCropImageUri;
    Bitmap selectedBitmap;
    Activity CURRENT_ACTIVITY = StoreEventsDetailsActivity.this;
    ArrayList<HashMap<String, String>> storeDetailsList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_event_details_layout);
        initialize();
        productsRecycler();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_ACTIVITY.finish();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Happy doing business with you. Our deivery agent as delivered the goods at your door step successfully. For enqueries plz do not hesitate to contact us on Phone:0987654321,Address 123 Abc building,Abc street,xyz-641146";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }


    private void productsRecycler() {
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            hashMap.put("detail", "Ashivad aata 50Kg");
            storeDetailsList.add(hashMap);

        }
        storeDeatilsAdapter = new StoreDeatilsAdapter(CURRENT_ACTIVITY, storeDetailsList);
        productsRecycler.setLayoutManager(new LinearLayoutManager(CURRENT_ACTIVITY));
        productsRecycler.setAdapter(storeDeatilsAdapter);

    }


    private void initialize() {
        productsRecycler = findViewById(R.id.products_recycler);
        back = findViewById(R.id.back);
        cashCollect = findViewById(R.id.cash_collect_layout);
        share = findViewById(R.id.share_layout);
        map_layout = findViewById(R.id.map_layout);
        cal_layout = findViewById(R.id.cal_layout);
        Complete_lay = findViewById(R.id.Complete_lay);
        Re_Attempt_lay = findViewById(R.id.Re_Attempt_lay);
        cancel_lay = findViewById(R.id.cancel_lay);
        store_image = findViewById(R.id.store_image);
        more_con = findViewById(R.id.more_con);
        cashCollect.setVisibility(View.GONE);
    }
}
