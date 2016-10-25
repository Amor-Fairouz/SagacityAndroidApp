package com.esprit.sagacity.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.esprit.sagacity.R;
import com.esprit.sagacity.utils.ImageLoader2;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by amor on 03/01/2016.
 */
public class SingleItemViewQuotes extends ActionBarActivity {
    String quote;
    ImageLoader2 imageLoader = new ImageLoader2(this);
    String description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview_quotes);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        // Get the intent from ListViewAdapter
        quote = i.getStringExtra("quote");

        // Locate the ImageView in singleitemview.xml
        ImageView imgauthor = (ImageView) findViewById(R.id.quote);
        // Load image into the ImageView
        imageLoader.DisplayImage(quote, imgauthor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quote_byone, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.action_share:
                /*ShareLinkContent content=new ShareLinkContent.Builder().setContentUrl(Uri.parse(quote)).build();
                ShareDialog.show(SingleItemViewQuotes.this, content);*/
                setupFacebookShareIntent();
               break;




        }


        return true;
    }
    public void setupFacebookShareIntent() {
        ShareDialog shareDialog;
        FacebookSdk.sdkInitialize(getApplicationContext());
        shareDialog = new ShareDialog(this);

        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentTitle("image")
                .setContentDescription(
                        "\"Body Of Test Post\"")
                .setContentUrl(Uri.parse(quote))
                .build();

        shareDialog.show(linkContent);
    }


}