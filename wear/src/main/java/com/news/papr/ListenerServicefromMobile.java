package com.news.papr;


import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import android.content.Intent;

public class ListenerServicefromMobile extends WearableListenerService {

    private static final String HELLO_WORLD_MOBILE_PATH = "/hello-world-mobile";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

        /*
         * Receive the message from wear
         */
        if (messageEvent.getPath().equals(HELLO_WORLD_MOBILE_PATH)) {

            Intent startIntent = new Intent(this, WearNewsPaprActivity.class);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startIntent);
        }

    }

}


