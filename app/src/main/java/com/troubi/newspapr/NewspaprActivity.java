package com.troubi.newspapr;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.ImageView;
import android.widget.TextView;

public class NewspaprActivity extends Activity {

    private static final int REQUEST_CODE_READING = 1337;

    private TextView mTitle;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspapr);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTitle = (TextView) stub.findViewById(R.id.newspapr_title);
                mImage = (ImageView) stub.findViewById(R.id.newspapr_image);
            }
        });

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.dummy_background);

        String text = "Thank you, everyone. It’s great to see all of you. Welcome to Google I/O. Every year, we look forward to this date. We’ve been hard at work since last I/O evolving our platforms so that developers like you can build amazing experiences. So thank you for joining us in person. I/O is a pretty global event. We have viewing parties in over 597 locations in 85 countries, in six continents. And there are over one million people watching this on the live stream today. Let’s say hello to a few locations.";
        startReadingActivity(text.replaceAll("\\s+", " ").split(" "), icon);
    }

    private void startReadingActivity(String[] text, Bitmap image) {
        Intent reading = new Intent(this, ReadingActivity.class);
        reading.putExtra(ReadingActivity.INTENT_EXTRA_TEXT, text);
        reading.putExtra(ReadingActivity.INTENT_EXTRA_IMAGE, image);

        startActivityForResult(reading, REQUEST_CODE_READING);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_READING) {
            if (resultCode == RESULT_OK) {

            }
        }
    }
}
