package com.goblax.lastmile.BasicActivities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.goblax.lastmile.R;
import com.goblax.lastmile.Utils.SharePref;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Login_Activity extends AppCompatActivity {
    private Activity CURRENT_ACTIVITY = Login_Activity.this;
    private RelativeLayout Login;
    private AppCompatEditText username, password;
    private TextView to_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        Initialize();


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().equals("") || !(username.getText().toString().length() == 10)) {
                    Toast.makeText(CURRENT_ACTIVITY, "Invalid NO!!", Toast.LENGTH_SHORT).show();
                    String phonenumber = "+91" + username.getText().toString();
                } else if (password.getText().toString().equals("")) {
                    Toast.makeText(CURRENT_ACTIVITY, "Enter your valid 4 digit Pin", Toast.LENGTH_SHORT).show();
                } else {

                    getPermission();

                }
            }

        });

    }

    private void getPermission() {
        Dexter.withContext(CURRENT_ACTIVITY)
                .withPermission(ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        startActivity(new Intent(CURRENT_ACTIVITY, FragmentsActivity.class));
                        SharePref.setFromTab(CURRENT_ACTIVITY, "1");
                        CURRENT_ACTIVITY.finish();
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

    @Override
    protected void onStart() {
        super.onStart();
        //check for app update
    }

    @SuppressLint("WrongViewCast")
    private void Initialize() {
        to_register = (TextView) findViewById(R.id.signup_text);
        username = (AppCompatEditText) findViewById(R.id.username);
        password = (AppCompatEditText) findViewById(R.id.password);
        Login = (RelativeLayout) findViewById(R.id.login_btn);
        username.setRawInputType(Configuration.KEYBOARD_12KEY);
        password.setRawInputType(Configuration.KEYBOARD_12KEY);
    }
}
