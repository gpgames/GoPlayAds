/*
 * Created by GoPlay Games.
 * @goplay.mobilegames
 * Copyright (c) 2018.
 */

package com.goplay.ads.listener;

import android.view.View;

@SuppressWarnings("unused")
public interface NativeAdListener {

    void onAdLoaded();
    void onAdLoadFailed(Exception e);

    interface CallToActionListener{
        void onCallToActionClicked(View view);
    }
}
