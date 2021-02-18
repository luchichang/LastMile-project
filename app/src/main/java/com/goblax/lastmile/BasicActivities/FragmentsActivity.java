package com.goblax.lastmile.BasicActivities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.goblax.lastmile.Fragments.HomeFragment;
import com.goblax.lastmile.Fragments.MapFragment;
import com.goblax.lastmile.Fragments.ProfileFragment;
import com.goblax.lastmile.Fragments.TaskFragment;
import com.goblax.lastmile.R;
import com.goblax.lastmile.Utils.SharePref;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentsActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        initialize();
        fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bot_home:
                        fragment = new HomeFragment();
                        SharePref.setFromTab(FragmentsActivity.this,"1");
                        break;
//                    case R.id.bot_map:
//                        fragment = new MapFragment();
//                        break;
                    case R.id.bot_task:
                        fragment = new TaskFragment();
                        SharePref.setFromTab(FragmentsActivity.this,"2");
                        break;
                    case R.id.bot_profile:
                        fragment = new ProfileFragment();
                        break;

                    default:SharePref.setFromTab(FragmentsActivity.this,"1");

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;
            }

        });

    }

    private void initialize() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

    }
}
