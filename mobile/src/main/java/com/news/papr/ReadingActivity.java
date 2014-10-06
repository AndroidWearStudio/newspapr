package com.news.papr;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

public class ReadingActivity extends Activity implements Runnable {

    public static final String INTENT_EXTRA_IMAGE = "image";
    public static final String INTENT_EXTRA_TEXT = "text";

    /**
     * Word display time in milliseconds
     */
    private static final int WORD_DISPLAY_TIME = 200;

    private static final int WORD_DISPLAY_TIME_EXTRA = 100;

    /**
     * The first word is displayed a bit longer for orientation
     */
    private static final int STARTING_DELAY = 1000;


    /**
     * The last word is displayed a bit longer for orientation
     */
    private static final int ENDING_DELAY = 1500;

    /**
     * The highlighted char will be roughly at RELATIVE_HIGHLIGHT_POSITION * word.length
     */
    private static final double RELATIVE_HIGHLIGHT_POSITION = .3;

    private TextView mText;
    private ImageView mImage;

    private String[] mTextArray;
    private int mTextArrayPosition;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);


        mText = (TextView) findViewById(R.id.reading_text);
        mImage = (ImageView) findViewById(R.id.reading_image);

        mTextArray = getIntent().getExtras().getStringArray(INTENT_EXTRA_TEXT);

        mText.setText(mTextArray[0]);
        mTextArrayPosition = 0;
        Bitmap image = (Bitmap) getIntent().getExtras().getParcelable(INTENT_EXTRA_IMAGE);
        mImage.setImageBitmap(Utility.createBlurImage(ReadingActivity.this, image));


        mHandler.postDelayed(ReadingActivity.this, STARTING_DELAY);
    }


    @Override
    public void run() {
        mTextArrayPosition++;

        String word = mTextArray[mTextArrayPosition];
        mText.setText(highlightPivotPosition(word));

        if (mTextArrayPosition < mTextArray.length - 1) {
            int delay = WORD_DISPLAY_TIME;

            if (isExtraTimeWord(word)) {
                delay += WORD_DISPLAY_TIME_EXTRA;
            }

            mHandler.postDelayed(this, delay);
        } else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startNewspaprActivity();
                }
            }, ENDING_DELAY);

        }
    }

    /**
     * Determines whether a word is legitimate to be shown
     * for some extra time
     *
     * @param word word to test
     * @return true if extra time is granted, wrong otherwise.
     */
    private boolean isExtraTimeWord(String word) {
        return word.contains(",") || word.contains(".");
    }

    private void startNewspaprActivity() {
        setResult(RESULT_OK);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private Spannable highlightPivotPosition(String text) {
        SpannableStringBuilder word = new SpannableStringBuilder(text);
        int highlightPosition = (int) Math.floor(text.length() * RELATIVE_HIGHLIGHT_POSITION);

        if (highlightPosition >= text.length()) {
            highlightPosition = text.length() - 1;
        }

        word.setSpan(
                new ForegroundColorSpan(Color.RED),
                highlightPosition,
                highlightPosition + 1,
                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        return word;
    }
}