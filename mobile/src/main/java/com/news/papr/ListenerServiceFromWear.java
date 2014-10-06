package com.news.papr;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import com.google.gson.Gson;

import com.news.papr.model.Article;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListenerServiceFromWear extends WearableListenerService {

    private static final String HELLO_WORLD_WEAR_PATH = "/hello-world-mobile";

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        /*
         * Receive the message from wear
         */
        if (messageEvent.getPath().equals(HELLO_WORLD_WEAR_PATH)) {

            RestClient.getArticles(callback);
        }

    }

    Callback<List<Article>> callback = new Callback<List<Article>>() {

        @Override
        public void success(List<Article> articles, Response response) {

            String articleJson = new Gson().toJson(articles);
            Intent intent = new Intent(NewsPaprActivity.BROADCAST_MSG);
            intent.putExtra("json", articleJson);

            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

        }

        @Override
        public void failure(RetrofitError error) {
            Log.e("Newspapr", error.toString());
        }
    };


}