package com.troubi.newspapr;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.wearable.view.WatchViewStub;
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

    /**
     * The first word is displayed a bit longer for orientation
     */
    private static final int STARTING_DELAY = WORD_DISPLAY_TIME * 4;

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
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mText = (TextView) stub.findViewById(R.id.reading_text);
                mImage = (ImageView) stub.findViewById(R.id.reading_image);

                mTextArray = getIntent().getExtras().getStringArray(INTENT_EXTRA_TEXT);

                mText.setText(mTextArray[0]);
                mTextArrayPosition = 0;
                Bitmap image = (Bitmap) getIntent().getExtras().getParcelable(INTENT_EXTRA_IMAGE);
                mImage.setImageBitmap(image);
                mImage.setImageBitmap(createBlurImage());


                mHandler.postDelayed(ReadingActivity.this, STARTING_DELAY);
            }
        });
    }

    private Bitmap createBlurImage() {
        final Bitmap photo = ((BitmapDrawable) mImage.getDrawable())
                .getBitmap().copy(Bitmap.Config.ARGB_8888, true);
        final RenderScript rs = RenderScript.create(this);
        final Allocation input = Allocation
                .createFromBitmap(rs, photo, Allocation.MipmapControl.MIPMAP_NONE,
                        Allocation.USAGE_SCRIPT);
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur
                .create(rs, Element.U8_4(rs));
        script.setInput(input);
        script.setRadius(25f);
        script.forEach(output);
        output.copyTo(photo);
        return photo;
    }

    @Override
    public void run() {
        mTextArrayPosition++;

        String word = mTextArray[mTextArrayPosition];
        mText.setText(highlightPivotPosition(word));

        if (mTextArrayPosition < mTextArray.length - 1) {
            mHandler.postDelayed(this, WORD_DISPLAY_TIME);
        } else {
            startNewspaprActivity();
        }
    }

    private void startNewspaprActivity() {
        setResult(RESULT_OK);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private Spannable highlightPivotPosition(String text) {
        SpannableStringBuilder word = new SpannableStringBuilder(text);

        int highlightPosition = (int) Math.floor(text.length() * RELATIVE_HIGHLIGHT_POSITION);

        word.setSpan(
                new ForegroundColorSpan(Color.RED),
                highlightPosition,
                highlightPosition + 1,
                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        return word;
    }
}
