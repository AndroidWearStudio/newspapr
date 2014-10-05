package com.troubi.newspapr;

import android.content.Context;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WearableListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
* Created by michaeljakob on 10/5/14.
*/
public class CategoryAdapter extends WearableListView.Adapter {

    private static final String[] CATEGORIES = new String[]{
        "Sports", "Hot", "Entertainment", "Celebrities", "Technology", "World"
    };


    private static ArrayList<Integer> mIcons;

    static {
        mIcons = new ArrayList<Integer>();
        mIcons.add(R.drawable.sports);
        mIcons.add(R.drawable.hot);
        mIcons.add(R.drawable.movie);
        mIcons.add(R.drawable.star);
        mIcons.add(R.drawable.box);
        mIcons.add(R.drawable.world);
    }

    private float mDefaultCircleRadius;
    private float mSelectedCircleRadius;

    private CategoryActivity categoryActivity;

    public CategoryAdapter(CategoryActivity categoryActivity) {
        this.categoryActivity = categoryActivity;
    }

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new WearableListView.ViewHolder(new CategoryAdapter.MyItemView(categoryActivity));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder viewHolder, int i) {
        CategoryAdapter.MyItemView itemView = (CategoryAdapter.MyItemView) viewHolder.itemView;

        TextView txtView = (TextView) itemView.findViewById(R.id.text);
        txtView.setText(CATEGORIES[i]);

        Integer resourceId = CategoryAdapter.mIcons.get(i);
        CircledImageView imgView = (CircledImageView) itemView.findViewById(R.id.image);
        imgView.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return CATEGORIES.length;
    }


    private final class MyItemView extends FrameLayout implements WearableListView.Item {

        final CircledImageView imgView;
        final TextView txtView;
        private float mScale;
        private final int mFadedCircleColor;
        private final int mChosenCircleColor;

        public MyItemView(Context context) {
            super(context);
            View.inflate(context, R.layout.row_advanced_item_layout, this);
            imgView = (CircledImageView) findViewById(R.id.image);
            txtView = (TextView) findViewById(R.id.text);
            mFadedCircleColor = getResources().getColor(android.R.color.darker_gray);
            mChosenCircleColor = getResources().getColor(android.R.color.holo_blue_dark);
        }

        @Override
        public float getProximityMinValue() {
            return mDefaultCircleRadius;
        }

        @Override
        public float getProximityMaxValue() {
            return mSelectedCircleRadius;
        }

        @Override
        public float getCurrentProximityValue() {
            return mScale;
        }

        @Override
        public void setScalingAnimatorValue(float value) {
            mScale = value;
            imgView.setCircleRadius(mScale);
            imgView.setCircleRadiusPressed(mScale);
        }

        @Override
        public void onScaleUpStart() {
            imgView.setAlpha(1f);
            txtView.setAlpha(1f);
            imgView.setCircleColor(mChosenCircleColor);
        }

        @Override
        public void onScaleDownStart() {
            imgView.setAlpha(0.5f);
            txtView.setAlpha(0.5f);
            imgView.setCircleColor(mFadedCircleColor);
        }
    }
}
