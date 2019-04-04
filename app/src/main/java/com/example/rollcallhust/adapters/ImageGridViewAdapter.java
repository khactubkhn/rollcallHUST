package com.example.rollcallhust.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rollcallhust.R;

import java.util.ArrayList;

public class ImageGridViewAdapter extends ArrayAdapter<Bitmap> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Bitmap> data = new ArrayList();

    public ImageGridViewAdapter(Context context, int layoutResourceId, ArrayList<Bitmap> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.imgRollCall);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        //ImageItem item = data.get(position);
        holder.image.setImageBitmap(data.get(position));
        return row;
    }

    static class ViewHolder {
        ImageView image;
    }
}
