package com.goplay.ads.sample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.button.MaterialButton;
import com.goplay.ads.GoPlayAdsNative;
import com.goplay.ads.listener.NativeAdListener;
import com.goplay.ads.sample.R;

public class NativeAd extends BaseFragment {
    public NativeAd() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.native_ad, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        boolean isShowLocalAssets = getContext().getSharedPreferences("localAssetsNative", Context.MODE_PRIVATE).getBoolean("value", false);

        rootView.findViewById(R.id.card_view).setVisibility(View.GONE);
        TextView loading = rootView.findViewById(R.id.loading);

        SwitchCompat localAssets = rootView.findViewById(R.id.useLocalResources);
        GoPlayAdsNative goplayAdsNative;
        if (!isShowLocalAssets) {
            rootView.findViewById(R.id.goplayAds_header_image).setVisibility(View.GONE);
            goplayAdsNative = new GoPlayAdsNative(getContext(), "http://bit.ly/goplayAds");
        }
        else goplayAdsNative = new GoPlayAdsNative(getContext(), R.raw.ad_assets);

        goplayAdsNative.setNativeAdView(rootView.findViewById(R.id.card_view))
                .usePalette(true)
                .setNativeAdListener(new NativeAdListener() {
                    @Override
                    public void onAdLoaded() {
                        loading.setVisibility(View.GONE);
                        rootView.findViewById(R.id.card_view).setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdLoadFailed(@NonNull Exception e) {
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

        localAssets.setChecked(getContext().getSharedPreferences("localAssetsNative", Context.MODE_PRIVATE).getBoolean("value", false));
        localAssets.setOnCheckedChangeListener((buttonView, isChecked) -> {
            getContext().getSharedPreferences("localAssetsNative", Context.MODE_PRIVATE).edit().putBoolean("value", isChecked).apply();
            getActivity().recreate();
        });

    }
}
