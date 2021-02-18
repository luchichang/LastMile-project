package com.goblax.lastmile.BasicActivities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

import com.goblax.lastmile.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SplashScreen extends AppCompatActivity {
    private Activity CURRENT_ACTIVITY = SplashScreen.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                Intent i = new Intent(CURRENT_ACTIVITY, Login_Activity.class);

                startActivity(i);

                // close this activity

                finish();

            }

        }, 3 * 1000); // wait for 3 seconds


    }
}
