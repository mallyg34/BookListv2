package com.example.android.booklistingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static android.R.id.title;

/**
 * Created by Daguru34 on 4/21/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {


    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        Book currentBook = getItem(position);

        //ImageView image = (ImageView) listItemView.findViewById(R.id.imageView);
        //image.setImageResource(R.drawable.download);

        TextView title = (TextView) listItemView.findViewById(R.id.title_view);
        title.setText(currentBook.getmTitle());

        TextView author = (TextView) listItemView.findViewById(R.id.author_view);
        author.setText(currentBook.getmAuthor());

        TextView pages = (TextView) listItemView.findViewById(R.id.page_count_view);
        pages.setText(Integer.toString(currentBook.getmPageCount()));

        return listItemView;
    }
}
