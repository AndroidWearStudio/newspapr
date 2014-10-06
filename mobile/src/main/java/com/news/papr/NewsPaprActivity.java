package com.news.papr;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mariux.teleport.lib.TeleportClient;
import com.news.papr.model.Article;


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


        Article[] articles = fetchArticles(this);

        loadContent(articles);
    }


    private static Article[] fetchArticles(Context ctx) {

        Article[] a = new Article[9];

        Article a1 = new Article();
        a1.title = "article #1 title";
        a1.text = "Thank you, everyone. It’s great to see all of you. Welcome to Google I/O. Every year, we look forward to this date. We’ve been hard at work since last I/O evolving our platforms so that developers like you can build amazing experiences. So thank you for joining us in person. I/O is a pretty global event. We have viewing parties in over 597 locations in 85 countries, in six continents. And there are over one million people watching this on the live stream today. Let’s say hello to a few locations.";
        a1.image = BitmapFactory.decodeResource(ctx.getResources(),
                R.drawable.dummy_background);


        Article a2 = new Article();
        a2.title = "article #2 title";
        a2.text = "article #2 text";
        a2.image = BitmapFactory.decodeResource(ctx.getResources(),
                R.drawable.dummy_background);

        Article a3 = new Article();
        a3.title = "article #3 title";
        a3.text = "article #3 text";
        a3.image = BitmapFactory.decodeResource(ctx.getResources(),
                R.drawable.dummy_background);



        a[0] = a1;
        a[1] = a2;
        a[2] = a3;
        a[3] = a1;
        a[4] = a2;
        a[5] = a3;
        a[6] = a1;
        a[7] = a2;
        a[8] = a3;

        // ATTENTION: RETURN AT LEAST 9 ITEMS

        return a;
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


    private void loadContent(final Article[] articles) {
        Button[] buttons = getCategoryButtons();

        int i = 0;
        for (Button b : buttons) {
            final int in = i;
            b.setText(articles[in].title);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent reading = new Intent(NewsPaprActivity.this, ReadingActivity.class);
                    reading.putExtra(ReadingActivity.INTENT_EXTRA_TEXT, articles[in].text.split(" "));
                    reading.putExtra(ReadingActivity.INTENT_EXTRA_IMAGE, articles[in].image);


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
