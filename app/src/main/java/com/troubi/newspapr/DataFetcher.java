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

    private static Article[] fetchArticles(Context ctx) {

        Article[] a = new Article[3];

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

        return a;
    }

    public static int getNumArticles() {
        return 3;
    }

    public static Article getArticle(Context ctx, int id) {
        return fetchArticles(ctx)[id];
    }
}
