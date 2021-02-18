package com.goblax.lastmile.BasicActivities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goblax.lastmile.Adapters.StoreDeatilsAdapter;
import com.goblax.lastmile.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import java.util.ArrayList;
import java.util.HashMap;

import static android.Manifest.permission.CAMERA;

public class StoreDetailsActivity extends AppCompatActivity {
    private PowerMenu powerMenu;
    RelativeLayout cashCollect, share,back, map_layout, cal_layout, Complete_lay, more_con, Re_Attempt_lay, cancel_lay;
    RecyclerView productsRecycler;
    public static ImageView store_image;
    StoreDeatilsAdapter storeDeatilsAdapter;
    LinearLayoutManager linearLayoutManager;
    private static final int CAMERA_REQUEST = 1888;
    private Uri mCropImageUri;
    Bitmap selectedBitmap;
    int picture_captured = 0;
    Activity CURRENT_ACTIVITY = StoreDetailsActivity.this;
    ArrayList<HashMap<String, String>> storeDetailsList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_details_layout);
        initialize();
        productsRecycler();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_ACTIVITY.finish();
            }
        });
        cashCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_ACTIVITY.finish();
            }
        });

        cal_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" + "8098548757");
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                startActivity(i);
            }
        });

        more_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerMenu = new PowerMenu.Builder(CURRENT_ACTIVITY)
                        .addItem(new PowerMenuItem("Deliver All")) // add an item.
                        .addItem(new PowerMenuItem("Return All/RTO")) // add an item.
                        .addItem(new PowerMenuItem("Re-attempt All")) // add an item.
                        .setAnimation(MenuAnimation.SHOWUP_BOTTOM_RIGHT) // Animation start point (TOP | LEFT).
                        .setMenuRadius(10f) // sets the corner radius.
                        .setMenuShadow(10f) // sets the shadow.
                        .setTextColor(ContextCompat.getColor(CURRENT_ACTIVITY, R.color.black))
                        .setTextGravity(Gravity.LEFT)
                        .setTextSize(12)
                        .setTextTypeface(Typeface.create("nunito_sans_light", Typeface.NORMAL))
                        .setSelectedTextColor(Color.WHITE)
                        .setMenuColor(Color.WHITE)
                        .setSelectedMenuColor(ContextCompat.getColor(CURRENT_ACTIVITY, R.color.colorPrimary))
                        .setBackgroundColor(CURRENT_ACTIVITY.getResources().getColor(R.color.grey))
                        .setOnMenuItemClickListener(new OnMenuItemClickListener<PowerMenuItem>() {
                            @Override
                            public void onItemClick(int position1, PowerMenuItem item) {


                                if (item.getTitle().equals("Deliver All")) {
                                    getResult();

                                } else if (item.getTitle().equals("Return All/RTO")) {
                                    Toast.makeText(CURRENT_ACTIVITY, "Please return the product to warehouse", Toast.LENGTH_SHORT).show();

                                } else if (item.getTitle().equals("Re-attempt All")) {
                                    Toast.makeText(CURRENT_ACTIVITY, "Thanks for your effort", Toast.LENGTH_SHORT).show();
                                }

                                powerMenu.dismiss();

                            }
                        })
                        .build();

                powerMenu.showAsDropDown(v);

            }
        });

        map_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=an+prozone+coimbatore"));
                startActivity(intent);
            }
        });

        Complete_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResult();
            }
        });

        Re_Attempt_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CURRENT_ACTIVITY, "Thanks for your effort", Toast.LENGTH_SHORT).show();
            }
        });

        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CURRENT_ACTIVITY, "Please return the product to warehouse", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (!(selectedBitmap == null)) {
            store_image.setImageBitmap(selectedBitmap);
        }

    }

    private void getResult() {
        LayoutInflater inflater = (LayoutInflater) CURRENT_ACTIVITY.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertLayout = inflater.inflate(R.layout.confirm_dialog, null);
        final AlertDialog alertforremove = new AlertDialog.Builder(CURRENT_ACTIVITY).create();
        alertforremove.setView(alertLayout);

        RelativeLayout confirm = (RelativeLayout) alertLayout.findViewById(R.id.logout);
        RelativeLayout cancel = (RelativeLayout) alertLayout.findViewById(R.id.no);
        store_image = alertLayout.findViewById(R.id.store_pic);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(selectedBitmap == null)) {
                    alertforremove.dismiss();
                    CURRENT_ACTIVITY.finish();
                } else {
                    Toast.makeText(CURRENT_ACTIVITY, "Please upload picture of the store!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        alertforremove.show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            selectedBitmap = (Bitmap) data.getExtras().get("data");
            store_image.setImageBitmap(selectedBitmap);
            picture_captured = 1;
        }
    }


    private void selectImage() {
        Dexter.withContext(CURRENT_ACTIVITY)
                .withPermission(CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
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
