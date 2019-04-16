package com.goplay.goplayAds.sample.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.goplay.ads.GoPlayAdsNative;
import com.goplay.ads.listener.NativeAdListener;
import com.goplay.goplayAds.sample.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NativeAd extends Fragment {

    public NativeAd() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.native_ad, container, false);

        rootView.findViewById(R.id.card_view).setVisibility(View.GONE);
        TextView loading = rootView.findViewById(R.id.loading);
        GoPlayAdsNative goplayAdsNative = new GoPlayAdsNative(getContext(), "http://bit.ly/goplayAds");
        goplayAdsNative.setNativeAdView(rootView.findViewById(R.id.card_view));
        goplayAdsNative.usePalette(true);
        goplayAdsNative.setNativeAdListener(new NativeAdListener() {
            @Override
            public void onAdLoaded() {
                loading.setVisibility(View.GONE);
                rootView.findViewById(R.id.card_view).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdLoadFailed(Exception e) {
                loading.setText(String.format("%s%s", getString(R.string.ad_failed), e.getMessage()));
                loading.setVisibility(View.VISIBLE);
            }
        });

        MaterialButton load = rootView.findViewById(R.id.load);
        load.setOnClickListener(v -> {
            rootView.findViewById(R.id.card_view).setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
            goplayAdsNative.loadAds();
        });

        return rootView;
    }
}
