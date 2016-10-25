package com.esprit.sagacity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.esprit.sagacity.R;
import com.esprit.sagacity.utils.ImageLoader;

/**
 * Created by amor on 30/11/2015.
 */
public class SingleItemView extends ActionBarActivity {
    String author;
   String Description;
    ImageLoader imageLoader = new ImageLoader(this);
    String showQuote;
    String idAuthor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        // Get the intent from ListViewAdapter
        author = i.getStringExtra("author");
        Description = i.getStringExtra("Description");
        idAuthor=i.getStringExtra("idAuthor");
        // Locate the ImageView and TextView in singleitemview.xml
        ImageView imgauthor = (ImageView) findViewById(R.id.author);
       TextView description=(TextView) findViewById(R.id.Description);

        // Load image into the ImageView and text into the textview
        imageLoader.DisplayImage(author, imgauthor);
        ScrollView scroller=new ScrollView(this);
        //scroller.addView(description);
        description.setMovementMethod(ScrollingMovementMethod.getInstance());

        description.setText(Description);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manu_parse_author, menu);

return true;

    }

    public void doShow() {

        //Intent intent = new Intent(Intent.ACTION_SEND);

        Intent intent = new Intent(SingleItemView.this,
                QuotesByOneAuthor.class);
        intent.putExtra("idAuthor",idAuthor);
        System.out.println("***************test object id********************" + idAuthor);

        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;

            case R.id.showquote:

                doShow();

                break;


                }


        return true;
    }




}
