package com.news.papr;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.mariux.teleport.lib.TeleportClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class NewsPaprActivity extends Activity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String HELLO_WORLD_MOBILE_PATH = "/hello-world-mobile";


    private TeleportClient mTeleportClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_papr);

        //Connect the GoogleApiClient
        mTeleportClient = new TeleportClient(this);

        loadContent();
    }

    private void loadContent() {
        Button[] buttons = getCategoryButtons();


        int i = 0;
        for (Button b : buttons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent reading = new Intent(NewsPaprActivity.this, ReadingActivity.class);
                    // reading.putExtra(ReadingActivity.INTENT_EXTRA_TEXT, text[i]);
                    


                    startActivity(reading);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            });
            i++;
        }
    }


    private Button[] getCategoryButtons() {
        Button[] buttons = new Button[9];
        buttons[0] = (Button) findViewById(R.id.category1);
        buttons[1] = (Button) findViewById(R.id.category2);
        buttons[2] = (Button) findViewById(R.id.category3);
        buttons[3] = (Button) findViewById(R.id.category4);
        buttons[4] = (Button) findViewById(R.id.category5);
        buttons[5] = (Button) findViewById(R.id.category6);
        buttons[6] = (Button) findViewById(R.id.category7);
        buttons[7] = (Button) findViewById(R.id.category8);
        buttons[8] = (Button) findViewById(R.id.category9);


        return buttons;
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
