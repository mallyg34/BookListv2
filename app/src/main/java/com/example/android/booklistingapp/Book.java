package com.example.android.booklistingapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by Daguru34 on 4/21/2017.
 */

public class Book implements Parcelable {

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

    protected Book(Parcel in) {
        mPageCount = in.readInt();
        mTitle = in.readString();
        mAuthor = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mPageCount);
        dest.writeString(mTitle);
        dest.writeString(mAuthor);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
