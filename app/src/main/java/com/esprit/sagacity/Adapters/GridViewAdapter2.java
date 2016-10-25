package com.esprit.sagacity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.esprit.sagacity.Model.QuotesList;
import com.esprit.sagacity.R;
import com.esprit.sagacity.activities.SingleItemViewQuotes;
import com.esprit.sagacity.utils.ImageLoader2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amor on 01/12/2015.
 */
public class GridViewAdapter2 extends BaseAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ImageLoader2 imageLoader;
    private List<QuotesList> quotearraylist = null;
    private ArrayList<QuotesList> arraylist;
    public GridViewAdapter2(Context context, List<QuotesList> quotearraylist) {
        this.context = context;
        this.quotearraylist = quotearraylist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<QuotesList>();
        this.arraylist.addAll(quotearraylist);
        imageLoader = new ImageLoader2(context);
    }

    public class ViewHolder {
        ImageView quote;
    }


    @Override
    public int getCount() {
        return quotearraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return quotearraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.gridview_item_quote, null);
            // Locate the ImageView in gridview_item.xml
            holder.quote = (ImageView) view.findViewById(R.id.quote);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Load image into GridView
        imageLoader.DisplayImage(quotearraylist.get(position).getQuote(),
                holder.quote);
        // Capture GridView item click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemViewQuotes.class);
                // Pass all data phone
                intent.putExtra("quote", quotearraylist.get(position)
                        .getQuote());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
