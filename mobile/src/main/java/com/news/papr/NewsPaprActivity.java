package com.news.papr;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.mariux.teleport.lib.TeleportClient;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;


public class NewsPaprActivity extends Activity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String HELLO_WORLD_MOBILE_PATH = "/hello-world-mobile";

    public static final String BROADCAST_MSG = "broadcast";


    TeleportClient mTeleportClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_papr);

        //Connect the GoogleApiClient
        mTeleportClient = new TeleportClient(this);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver, new IntentFilter(BROADCAST_MSG));
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String json = intent.getStringExtra("json");
            sendMessage(json.getBytes());
        }
    };


    /**
     * Send message to mobile handheld
     */
    private void sendMessage(byte[] payload) {

        if (mTeleportClient.getGoogleApiClient() != null && mTeleportClient.getGoogleApiClient()
                .isConnected()) {

            mTeleportClient.sendMessage(HELLO_WORLD_MOBILE_PATH, payload);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTeleportClient.connect();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mTeleportClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //Improve your code
    }
}
