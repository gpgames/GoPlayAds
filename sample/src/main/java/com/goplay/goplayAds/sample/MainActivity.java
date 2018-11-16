package com.goplay.goplayAds.sample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.goplay.ads.GoPlayAdsDialog;
import com.goplay.ads.GoPlayAdsInterstitial;
import com.goplay.ads.helper.GoPlayAdsHelper;
import com.goplay.ads.listener.AdListener;

public class MainActivity extends AppCompatActivity {

    private GoPlayAdsInterstitial interstitial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String adURL = "http://bit.ly/goplayAds";
        final TextView txt = findViewById(R.id.txt);

        final GoPlayAdsDialog goplayAds = new GoPlayAdsDialog(MainActivity.this);
        goplayAds.setUrl(adURL);
        goplayAds.hideIfAppInstalled(false);
        goplayAds.setCardCorners(100);
        goplayAds.setCtaCorner(100);
        goplayAds.setForceLoadFresh(false);
        goplayAds.showHeaderIfAvailable(false);
        goplayAds.setAdListener(new AdListener() {
            @Override
            public void onAdLoadFailed() {
                Toast.makeText(MainActivity.this, "Ad Loading Failed, retrying again ", Toast.LENGTH_SHORT).show();
                goplayAds.loadAds();
            }

            @Override
            public void onAdLoaded() {
                goplayAds.showAd();
            }

            @Override
            public void onAdClosed() {}

            @Override
            public void onAdShown() {
                Toast.makeText(MainActivity.this, "AdShown", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplicationLeft() {}
        });




        final GoPlayAdsDialog goplayAdsDialog = new GoPlayAdsDialog(this);
        goplayAdsDialog.setUrl(adURL);
        goplayAdsDialog.setCardCorners(50);
        goplayAdsDialog.setCtaCorner(50);
        goplayAdsDialog.setForceLoadFresh(true);
        goplayAdsDialog.hideIfAppInstalled(false);
        goplayAdsDialog.showHeaderIfAvailable(true);
        goplayAdsDialog.setAdListener(new AdListener() {
            @Override
            public void onAdLoadFailed() {
                goplayAdsDialog.loadAds();
            }

            @Override
            public void onAdLoaded() {
                goplayAdsDialog.showAd();
            }

            @Override
            public void onAdClosed() {}

            @Override
            public void onAdShown() {
                Toast.makeText(MainActivity.this, "AdShown", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplicationLeft() {}
        });

        interstitial = new GoPlayAdsInterstitial(MainActivity.this);
        interstitial.setUrl(adURL);
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoadFailed() {
                interstitial.loadAd();
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onAdLoaded() {
                txt.setText("Interstitial Ad Loaded");
                findViewById(R.id.button3).setEnabled(true);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onAdClosed() {
                txt.setText("Interstitial Ad Closed");
                findViewById(R.id.button3).setEnabled(false);
                interstitial.loadAd();
            }

            @Override
            public void onAdShown() {
                Toast.makeText(MainActivity.this, "AdShown", Toast.LENGTH_SHORT).show();

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onApplicationLeft() {
                txt.setText("Interstitial Ad Closed");
                findViewById(R.id.button3).setEnabled(false);
                interstitial.loadAd();
                Toast.makeText(MainActivity.this, "Application Left", Toast.LENGTH_SHORT).show();
            }
        });
        interstitial.loadAd();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goplayAds.loadAds();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goplayAdsDialog.loadAds();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitial.isAdLoaded()) interstitial.show();
            }
        });

        findViewById(R.id.clearCache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoPlayAdsHelper.clearGlideCache(MainActivity.this);
            }
        });

        findViewById(R.id.nativeActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NativeAdActivity.class));
            }
        });
    }

}

