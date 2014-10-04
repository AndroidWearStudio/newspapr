package com.news.papr;


import com.news.papr.model.Article;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DataFetcher {

    public static final class Article2 {

        public String title;

        public String text;

        public Bitmap image;
    }

    private static Article2[] fetchArticles(Context ctx) {

        Article2[] a = new Article2[3];

        Article2 a1 = new Article2();
        a1.title = "article #1 title";
        a1.text = "Thank you, everyone. It’s great to see all of you. Welcome to Google I/O. "
                + "Every year, we look forward to this date. We’ve been hard at work since last I/O evolving our platforms so that developers like you can build amazing experiences. So thank you for joining us in person. I/O is a pretty global event. We have viewing parties in over 597 locations in 85 countries, in six continents. And there are over one million people watching this on the live stream today. Let’s say hello to a few locations.";
        a1.image = BitmapFactory.decodeResource(ctx.getResources(),
                R.drawable.dummy_background);

        Article2 a2 = new Article2();
        a2.title = "article #2 title";
        a2.text = "article #2 text";
        a2.image = BitmapFactory.decodeResource(ctx.getResources(),
                R.drawable.dummy_background);

        Article2 a3 = new Article2();
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

    public static Article2 getArticle(final Context ctx, int id) {

        Callback<List<Article>> callback = new Callback<List<Article>>() {

            @Override
            public void success(List<Article> articles, Response response) {
                for (Article contributor : articles) {
                    System.out.println(contributor.getImage()+ " (" + contributor.getTitle()+ ")");
                }

            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.toString());


            }
        };
        RestClient.getArticles(callback);


        return fetchArticles(ctx)[id];
    }
}
