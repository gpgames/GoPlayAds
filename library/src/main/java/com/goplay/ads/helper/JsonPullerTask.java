/*
 * Created by GoPlay Games.
 * @goplay.mobilegames
 * Copyright (c) 2018.
 */

package com.goplay.ads.helper;

import android.os.AsyncTask;
import android.util.Log;

public class JsonPullerTask extends AsyncTask<String, String, String> {
    private final String jsonUrl;
    private final JsonPullerListener listener;

    public JsonPullerTask(String url, JsonPullerListener jsonPullerListener) {
        this.jsonUrl = url;
        this.listener = jsonPullerListener;
    }

    @Override
    protected String doInBackground(String... p1) {
        return GoPlayAdsHelper.parseJsonObject(jsonUrl);
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onPostExecute(result);
        Log.d("Response", result);
    }

    public interface JsonPullerListener{
        void onPostExecute(String result);
    }
}