/*
 * Created by GoPlay Games.
 * @goplay.mobilegames
 * Copyright (c) 2018.
 */

package com.goplay.ads.modal;

public class InterstitialModal {

    private String interstitialImageUrl;
    private String packageOrUrl;

    public void setInterstitialImageUrl(String interstitialImageUrl) {
        this.interstitialImageUrl = interstitialImageUrl;
    }

    public void setPackageOrUrl(String packageName) {
        this.packageOrUrl = packageName;
    }

    public String getInterstitialImageUrl() {
        return interstitialImageUrl;
    }

    public String getPackageOrUrl() {
        return packageOrUrl;
    }
}
