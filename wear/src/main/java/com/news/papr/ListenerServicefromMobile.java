package com.news.papr;


import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.news.papr.model.Article;

import android.content.Intent;
import android.util.Log;

import java.util.List;

public class ListenerServicefromMobile extends WearableListenerService {

    private static final String HELLO_WORLD_MOBILE_PATH = "/hello-world-mobile";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

        /*
         * Receive the message from mobile
         */
        if (messageEvent.getPath().equals(HELLO_WORLD_MOBILE_PATH)) {

            String json = new String(messageEvent.getData());

            Intent intent = new Intent(this, WearNewsPaprActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("json", json);
            Log.e("YOL", json);
            Gson gson = new Gson();
            List<Article> a = gson.fromJson(json,
                    new TypeToken<List<Article>>
                            () {
                    }.getType());

            for (Article article: a){
                Log.e("kIWX", article.getText()+ "");
            }
            startActivity(intent);
        }

    }

}


