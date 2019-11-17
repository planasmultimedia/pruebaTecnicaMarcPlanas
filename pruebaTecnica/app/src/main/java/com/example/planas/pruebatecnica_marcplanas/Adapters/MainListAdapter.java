package com.example.planas.pruebatecnica_marcplanas.Adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.planas.pruebatecnica_marcplanas.Models.MainListItem;
import com.example.planas.pruebatecnica_marcplanas.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainListAdapter extends ArrayAdapter<MainListItem> {
    private static final String TAG = "MainListAdapter";

    private LayoutInflater mInflater;
    private int mLayoutResource;
    private Context mContext;
    public ArrayList<MainListItem> items;

    public MainListAdapter(@NonNull Context context, int resource, @NonNull List<MainListItem> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        mLayoutResource = resource;
        items = (ArrayList<MainListItem>) objects;
    }

    static class ViewHolder{
        TextView tv_name;
        MainListItem item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        Log.d(TAG, "getView: starting , position : " + position);
        View vi = convertView;
        final ViewHolder holder;
        vi = mInflater.inflate(mLayoutResource, container, false);
        holder = new ViewHolder();

        holder.tv_name = (TextView) vi.findViewById(R.id.pokemon_name);
        holder.tv_name.setText(items.get(position).getName());

        AssetManager am = mContext.getApplicationContext().getAssets();

        Typeface typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Montserrat-Bold.ttf"));

        holder.tv_name.setTypeface(typeface);
        return vi;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public MainListItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




}
