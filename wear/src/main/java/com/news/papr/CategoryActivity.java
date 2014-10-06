package com.news.papr;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.mariux.teleport.lib.TeleportClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryActivity extends Activity implements WearableListView.ClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private WearableListView mListView;

    private CategoryAdapter mAdapter;

    private static final String HELLO_WORLD_WEAR_PATH = "/hello-world-mobile";

    public static final String INTENT_EXTRA_CATEGORY = "category";


    TeleportClient mTeleportClient;

    /**
     * Send message to mobile handheld
     */
    private void sendMessage() {

        if (mTeleportClient.getGoogleApiClient() != null && mTeleportClient.getGoogleApiClient()
                .isConnected()) {

            mTeleportClient.sendMessage(HELLO_WORLD_WEAR_PATH, null);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        mAdapter = new CategoryAdapter(this);
        mTeleportClient = new TeleportClient(this);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mListView = (WearableListView) stub.findViewById(R.id.listView1);
                mListView.setAdapter(mAdapter);
                mListView.setClickListener(CategoryActivity.this);
            }
        });
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        Intent reading = new Intent(this, WearNewsPaprActivity.class);

        String category = ((TextView) viewHolder.itemView.findViewById(R.id.text)).getText()
                .toString();
        reading.putExtra(INTENT_EXTRA_CATEGORY, category);

        sendMessage();
        // startActivity(reading);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onTopEmptyRegionClick() {
        Toast.makeText(this, "You tapped Top empty area", Toast.LENGTH_SHORT).show();
    }

}