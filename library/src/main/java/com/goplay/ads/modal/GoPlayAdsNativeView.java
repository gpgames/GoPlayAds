/*
 * Created by GoPlay Games.
 * @goplay.mobilegames
 * Copyright (c) 2018.
 */

package com.goplay.ads.modal;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class GoPlayAdsNativeView {

    private TextView title, description, price;
    private ImageView icon, headerImage;
    private View cta;
    private RatingBar ratings;

    //Setters
    public void setTitleView(TextView title) {
        this.title = title;
    }

    public void setDescriptionView(TextView description) {
        this.description = description;
    }

    public void setPriceView(TextView price) {
        this.price = price;
    }

    public void setIconView(ImageView icon) {
        this.icon = icon;
    }

    public void setCallToActionView(View cta) {
        this.cta = cta;
    }

    public void setHeaderImageView(ImageView headerImage) {
        this.headerImage = headerImage;
    }

    public void setRatingsView(RatingBar ratings) {
        this.ratings = ratings;
    }

    //Getters
    public TextView getTitleView() {
        return title;
    }

    public TextView getDescriptionView() {
        return description;
    }

    public TextView getPriceView() {
        return price;
    }

    public ImageView getIconView() {
        return icon;
    }

    public View getCallToActionView() {
        return cta;
    }

    public ImageView getHeaderImageView() {
        return headerImage;
    }

    public RatingBar getRatingsView() {
        return ratings;
    }
}
