package com.goplay.goplayAds.sample;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.goplay.goplayAds.sample.adapter.ViewPagerAdapter;
import com.goplay.goplayAds.sample.fragments.DialogAd;
import com.goplay.goplayAds.sample.fragments.InterstitialAd;
import com.goplay.goplayAds.sample.fragments.NativeAd;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tabs);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    void showSnackbar(String msg) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), msg, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));

        TextView snackText = snackbarView.findViewById(R.id.snackbar_text);
        snackText.setTypeface(Typeface.SERIF, Typeface.BOLD);
        snackText.setGravity(Gravity.CENTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) snackText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        snackbar.show();
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DialogAd(), "Dialog");
        adapter.addFragment(new InterstitialAd(), "Interstitial");
        adapter.addFragment(new NativeAd(), "Native");
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 1) {
            ((InterstitialAd) adapter.getItem(1)).onBackPressed(MainActivity.this);
        } else super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
