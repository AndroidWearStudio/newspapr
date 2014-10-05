/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.news.papr;

import com.news.papr.model.Article;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ScreenSlidePageFragment extends Fragment {

    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";


    private static final int REQUEST_CODE_READING = 1337;


    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;



    private Article mArticle;


    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlidePageFragment create(Article article) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        fragment.setArticle(article);
        return fragment;
    }

    public ScreenSlidePageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
ViewGroup rootView = (ViewGroup) inflater
.inflate(R.layout.activity_newspapr, container, false);
//
//        final WatchViewStub stub = (WatchViewStub) rootView.findViewById(R.id.watch_view_stub);
//        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
//            @Override
//            public void onLayoutInflated(WatchViewStub stub) {
//                mTitle = (TextView) stub.findViewById(R.id.newspapr_title);
//                mImage = (ImageView) stub.findViewById(R.id.newspapr_image);
//
//                String category = getActivity().getIntent().getStringExtra(CategoryActivity
//                        .INTENT_EXTRA_CATEGORY);
//
//                mCurrentArticle = mArticleList.get(mPageNumber-1);
//                mTitle.setText(mCurrentArticle.getTitle());
//                // mImage.setImageBitmap(mCurrentArticle.getImage());
//
//                View.OnClickListener listener = new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startReadingActivity(
//                                mCurrentArticle.getText().replaceAll("\\s+", " ").split(" "));
//                                //,
//                                //mCurrentArticle.getImage());
//                    }
//                };
//
//                mTitle.setOnClickListener(listener);
//                mImage.setOnClickListener(listener);
//
//
//            }
//        });

        return rootView;
    }


    private void startReadingActivity(String[] text ) { // , Bitmap image
        Intent reading = new Intent(getActivity(), ReadingActivity.class);
        reading.putExtra(ReadingActivity.INTENT_EXTRA_TEXT, text);
        // reading.putExtra(ReadingActivity.INTENT_EXTRA_IMAGE, image);

        startActivityForResult(reading, REQUEST_CODE_READING);
        getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }

    public void setArticle(Article article) {
        mArticle = article;
    }

    public Article getArticle() {
        return mArticle;
    }
}
