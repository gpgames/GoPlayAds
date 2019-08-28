/*
 * Created by GoPlay Games.
 * @goplay.mobilegames
 * Copyright (c) 2018.
 */

package com.goplay.ads.listener;

public interface AdListener {
    void onAdLoadFailed(Exception e);
    void onAdLoaded();
    void onAdClosed();
    void onAdShown();
    void onApplicationLeft();
}