package com.example.android.booklistingapp;

import android.widget.ImageView;

/**
 * Created by Daguru34 on 4/21/2017.
 */

public class Book {

    private int mPageCount;
    private String mTitle;
    private String mAuthor;

    public Book(int mPageCount, String mTitle, String mAuthor) {
        this.mPageCount = mPageCount;
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
    }

    public int getmPageCount() {
        return mPageCount;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }
}
