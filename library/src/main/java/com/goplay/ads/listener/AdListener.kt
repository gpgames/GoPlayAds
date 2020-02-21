package com.goplay.ads.listener

interface AdListener {
    fun onAdLoadFailed(exception: Exception)
    fun onAdLoaded()
    fun onAdClosed()
    fun onAdShown()
    fun onApplicationLeft()
}
