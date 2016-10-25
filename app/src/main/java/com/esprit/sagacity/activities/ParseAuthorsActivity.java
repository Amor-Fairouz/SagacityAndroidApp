package com.esprit.sagacity.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;

import com.esprit.sagacity.R;
import com.esprit.sagacity.Model.AuthorsList;
import com.esprit.sagacity.Adapters.GridViewAdapter;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by amor on 02/01/2016.
 */

public class ParseAuthorsActivity extends ActionBarActivity {


    // Declare Variables
    GridView gridview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    GridViewAdapter adapter;
    private List<AuthorsList> authorarraylist = null;
    String name;
    String desciption;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from gridview_main.xml
        setContentView(R.layout.gridview_authors);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;}return true;}





    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ParseAuthorsActivity.this);
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
            authorarraylist = new ArrayList<AuthorsList>();
            try {
                // Locate the class table named "SamsungPhones" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                        "Authors");
                // Locate the column named "position" in Parse.com and order list
                // by ascending
                query.orderByAscending("position");
                ob = query.find();
                for (ParseObject country : ob) {
                    ParseFile image = (ParseFile) country.get("image");
                    name=country.getString("Name");
                    desciption=country.getString("Description");
                    country.getObjectId();
                    System.out.println("Test objectId"+country.getObjectId());
                    System.out.println("************ c'est url : " + image.getUrl());

                    AuthorsList map = new AuthorsList();
                    map.setAuthor(image.getUrl());
                    map.setName(name);
                    map.setIdAuthor(country.getObjectId());
                    map.setDescription(desciption);
                    authorarraylist.add(map);
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
            gridview = (GridView) findViewById(R.id.gridview);
            // Pass the results into ListViewAdapter.java
            adapter = new GridViewAdapter(ParseAuthorsActivity.this,
                    authorarraylist);
            // Binds the Adapter to the ListView
            gridview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}
