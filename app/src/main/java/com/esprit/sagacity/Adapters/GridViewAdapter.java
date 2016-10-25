package com.esprit.sagacity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.esprit.sagacity.Model.AuthorsList;
import com.esprit.sagacity.R;
import com.esprit.sagacity.activities.SingleItemView;
import com.esprit.sagacity.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amor on 30/11/2015.
 */
public class GridViewAdapter extends BaseAdapter{
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<AuthorsList> authorarraylist = null;
    private ArrayList<AuthorsList> arraylist;
    public GridViewAdapter(Context context, List<AuthorsList> authorarraylist) {
        this.context = context;
        this.authorarraylist = authorarraylist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<AuthorsList>();
        this.arraylist.addAll(authorarraylist);
        imageLoader = new ImageLoader(context);
    }

    public class ViewHolder {
        ImageView author;
        TextView nameauthor;
        TextView description;
    }


    @Override
    public int getCount() {
        return authorarraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return authorarraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.gridview_item, null);
            // Locate the ImageView in gridview_item.xml
            holder.author = (ImageView) view.findViewById(R.id.author);
            holder.nameauthor = (TextView)view.findViewById(R.id.nameAuthor);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Load image into GridView
        imageLoader.DisplayImage(authorarraylist.get(position).getAuthor(),
                holder.author);
        holder.nameauthor.setText(arraylist.get(position).getName());
        // Capture GridView item click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data
                intent.putExtra("author", authorarraylist.get(position)
                        .getAuthor());
                intent.putExtra("Description",authorarraylist.get(position)
                        .getDescription());
                intent.putExtra("idAuthor",authorarraylist.get(position).getIdAuthor());
                context.startActivity(intent);
            }
        });
        return view;
    }
}

