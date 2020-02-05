/*
 * Created by Darshan Pandya.
 * @itznotabug
 * Copyright (c) 2018.
 */

package com.goplay.ads.listener

import android.view.View

interface NativeAdListener {

    fun onAdLoaded()
    fun onAdLoadFailed(exception: Exception)

    interface CallToActionListener {
        fun onCallToActionClicked(view: View)
    }
}
