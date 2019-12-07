package com.vit.codevar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.vit.codevar.ui.ConsumablesFragment.ConsumablesFragment;
import com.vit.codevar.ui.HelpFragment.HelpFragment;
import com.vit.codevar.ui.InfoFragment.InfoFragment;
import com.vit.codevar.ui.NotficationFragment.NotificationFragment;
import com.vit.codevar.ui.TimelineFragment.TimelineFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    private BottomNavigationView navigationBar;
    public static TextView fragmentTitle;
    private ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setStatusBarColor(getApplicationContext().getColor(R.color.background));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationBar = findViewById(R.id.navigationBar);
        fragmentTitle = findViewById(R.id.fragmentTitle);
        profileImageView = findViewById(R.id.profileImageView);

        Glide
                .with(getApplicationContext())
                .load(LoginActivity.profileImage)
                .into(profileImageView);

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.navigationFragment, new TimelineFragment()).commit();

        navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment selectedFragment = null;

                switch (menuItem.getItemId())
                {
                    case R.id.navigation_timeline:
                        selectedFragment = new TimelineFragment();
                        break;

                    case R.id.navigation_consumables:
                        selectedFragment = new ConsumablesFragment();
                        break;

                    case R.id.navigation_notifications:
                        selectedFragment = new NotificationFragment();
                        break;

                    case R.id.navigation_help:
                        selectedFragment = new HelpFragment();
                        break;

                    case R.id.navigation_info:
                        selectedFragment = new InfoFragment();
                        break;
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.navigationFragment, selectedFragment)
                        .addToBackStack(null)
                        .commit();

                return true;
            }
        });
    }
}