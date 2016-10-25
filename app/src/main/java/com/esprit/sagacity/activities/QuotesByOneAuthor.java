package com.esprit.sagacity.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;

import com.esprit.sagacity.R;
import com.esprit.sagacity.Adapters.GridViewAdapter2;
import com.esprit.sagacity.Model.QuotesList;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by amor on 04/01/2016.
 */
public class QuotesByOneAuthor extends ActionBarActivity {

    // Declare Variables
    GridView gridview2;
    Context context;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    GridViewAdapter2 adapter;
    String idAuthor;
    private List<QuotesList> quotearraylist = null;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;





        }


        return true;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from gridview_main.xml
        setContentView(R.layout.gridview_quotes);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        // Get the intent from ListViewAdapter
       idAuthor = i.getStringExtra("idAuthor");
        // Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();
    }

    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(QuotesByOneAuthor.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Sagacity");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            quotearraylist = new ArrayList<QuotesList>();
            try {
                // Locate the class table named "Quotes" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                        "Quotes");
               // System.out.println("-----------------_________id------------------"+idAuthor);
               query.whereEqualTo("idAuthor", ParseObject.createWithoutData("Authors", idAuthor));
                // Locate the column name"d "position" in Parse.com and order list
                // by ascending
                query.orderByAscending("position");
                ob = query.find();
                for (ParseObject country : ob) {
                    ParseFile image = (ParseFile) country.get("image");

                    System.out.println("************ c'est url : "+image.getUrl());

                    QuotesList map = new QuotesList();
                    map.setQuote(image.getUrl());
                    quotearraylist.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the gridview in gridview_main.xml
            gridview2 = (GridView) findViewById(R.id.gridview2);
            // Pass the results into ListViewAdapter.java
            adapter = new GridViewAdapter2(QuotesByOneAuthor.this,
                    quotearraylist);
            // Binds the Adapter to the ListView
            gridview2.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }

    }

}
