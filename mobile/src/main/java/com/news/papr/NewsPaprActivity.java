package com.news.papr;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.mariux.teleport.lib.TeleportClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class NewsPaprActivity extends Activity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String HELLO_WORLD_MOBILE_PATH = "/hello-world-mobile";


    TeleportClient mTeleportClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_papr);

        //Connect the GoogleApiClient
        mTeleportClient = new TeleportClient(this);
    }


    /**
     * Send message to mobile handheld
     */
    private void sendMessage() {
        if (mTeleportClient.getGoogleApiClient() != null && mTeleportClient.getGoogleApiClient()
                .isConnected()) {

            mTeleportClient.sendMessage(HELLO_WORLD_MOBILE_PATH, null);

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
