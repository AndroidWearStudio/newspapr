package com.troubi.newspapr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by michaeljakob on 10/5/14.
 */
public class DataFetcher {

    public static final class Article {
        public String title;
        public String text;
        public Bitmap image;
    }

    public static Article[] fetchArticles(Context ctx) {

        Article[] a = new Article[3];

        Article a1 = new Article();
        a1.title = "article #1 title";
        a1.text = "article #1 text";
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

        return a;
    }
}
