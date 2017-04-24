package com.example.android.booklistingapp;

import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    //private static final String USGS_REQUEST_URL ="https://www.googleapis.com/books/v1/volumes?q=android&maxResults=10";
    private static String USGS_REQUEST_URL ="";
    public static final String LOG_TAG = MainActivity.class.getName();
    private BookAdapter mAdapter;
    public String searchWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar loadingData = (ProgressBar) findViewById(R.id.loading_indicator);
        loadingData.setVisibility(View.INVISIBLE);



        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        Button btn = (Button) findViewById(R.id.search_button);
        final EditText txt = (EditText) findViewById(R.id.editText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.v("EditText", txt.getText().toString());
                searchWord = txt.getText().toString();
                USGS_REQUEST_URL= "https://www.googleapis.com/books/v1/volumes?q=" + searchWord + "&maxResults=10";

                if( networkInfo != null && networkInfo.isConnected()) {
                    android.app.LoaderManager loaderManager = getLoaderManager();
                    loaderManager.initLoader(0, null, MainActivity.this);
                }else{
                    View loadingIndicator = findViewById(R.id.loading_indicator);
                    loadingIndicator.setVisibility(View.GONE);

                    TextView emptyText = (TextView) findViewById(R.id.empty_view);
                    emptyText.setText("no_internet_connection");

                }

            }
        });

        ListView bookListView = (ListView) findViewById(R.id.list_item_view);
        bookListView.setEmptyView(findViewById(R.id.empty_view));


        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
       // BookAdapter mAdapter = new BookAdapter(this, books);


        // Create a new adapter that takes an empty list of earthquakes as input
        //mAdapter = new BookAdapter(this, new ArrayList<Book>());


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        // TODO: Create a new loader for the given URL
        Log.v("MainActivity", "Step 1 In onCreateLoader");
        return new BookLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        // Loader reset, so we can clear out our existing data.
        Log.v("MainActivity", "Step 2 In onLoaderReset");
        mAdapter.clear();
        //android.app.LoaderManager loaderManager = getLoaderManager();
        //loaderManager.initLoader(0, null, MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader,List<Book> earthquakes) {
        // Clear the adapter of previous earthquake data
        Log.v("MainActivity", "Step 3 In onLoaderReset");
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }
       TextView emptyText = (TextView) findViewById(R.id.empty_view);
        emptyText.setText("Can't find content");
    }
}
