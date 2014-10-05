package com.troubi.newspapr;

import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WearableListView;
import android.view.ViewGroup;
import android.widget.TextView;

/**
* Created by michaeljakob on 10/5/14.
*/
public class CategoryAdapter extends WearableListView.Adapter {

    private static final String CATEGORIES = new String[]{
        "Sports", "Technology", "Politics", "Entertainment", "Economics", "Health"
    };

    private CategoryActivity categoryActivity;

    public CategoryAdapter(CategoryActivity categoryActivity) {
        this.categoryActivity = categoryActivity;
    }

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new WearableListView.ViewHolder(new CategoryActivity.MyItemView(categoryActivity));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder viewHolder, int i) {
        CategoryActivity.MyItemView itemView = (CategoryActivity.MyItemView) viewHolder.itemView;

        TextView txtView = (TextView) itemView.findViewById(R.id.text);
        txtView.setText(String.format("Item %d", i));

        Integer resourceId = CategoryActivity.mListItems.get(i);
        CircledImageView imgView = (CircledImageView) itemView.findViewById(R.id.image);
        imgView.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return CategoryActivity.mListItems.size();
    }
}
