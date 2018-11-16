/*
 * GoPlay Cross Promotion Ads.
 * Copyright (c) 2018.
 */

package com.goplay.goplayAds.sample;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.goplay.ads.GoPlayAdsNative;
import com.goplay.ads.listener.NativeAdListener;

public class NativeAdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.native_activity);
        final CardView card = findViewById(R.id.card_view);
        final Button load = findViewById(R.id.load);
        GradientDrawable drawable = (GradientDrawable) findViewById(R.id.goplayAds_cta).getBackground();
        drawable.setCornerRadius(100);

        /*GoPlayAdsNativeView nativeView = new GoPlayAdsNativeView();
        nativeView.setTitleView((TextView) findViewById(R.id.headline));
        nativeView.setDescriptionView((TextView) findViewById(R.id.body));
        nativeView.setIconView((ImageView) findViewById(R.id.app_icon));
        nativeView.setCallToActionView(findViewById(R.id.cta));
        nativeView.setPriceView((TextView) findViewById(R.id.price));
        nativeView.setRatingsView((RatingBar) findViewById(R.id.ratings));*/

        final GoPlayAdsNative goplayAdsNative = new GoPlayAdsNative(NativeAdActivity.this);
        goplayAdsNative.setUrl("http://bit.ly/goplayAds");
        goplayAdsNative.setNativeAdView(card);
        goplayAdsNative.setNativeAdListener(new NativeAdListener() {
            @Override
            public void onAdLoaded() {
                card.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdLoadFailed() {
                Toast.makeText(NativeAdActivity.this, "AdLoad Failed, Retrying again!", Toast.LENGTH_SHORT).show();
                //goplayAdsNative.loadAds();
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (card.getVisibility() == View.VISIBLE) card.setVisibility(View.GONE);
                goplayAdsNative.loadAds();
            }
        });
    }
}
