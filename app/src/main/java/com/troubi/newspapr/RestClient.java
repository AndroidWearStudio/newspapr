package com.troubi.newspapr;


import java.util.List;

import model.Article;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

public class RestClient {

    private static final String API_URL = "http://newspapr-ohg.rhcloud.com";


    interface NewsPapr {

        @GET("/")
        void getArticles(Callback<List<Article>> callback);
    }

    public static void getArticles(Callback<List<Article>> callback) {
        // Create a very simple REST adapter which points the GitHub API endpoint.
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        // Create an instance of our GitHub API interface.
        NewsPapr newsPapr = restAdapter.create(NewsPapr.class);

        // Fetch and print a list of the contributors to this library.
        newsPapr.getArticles(callback);

    }
}
