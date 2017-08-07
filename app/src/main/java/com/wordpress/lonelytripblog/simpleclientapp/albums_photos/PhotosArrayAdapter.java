package com.wordpress.lonelytripblog.simpleclientapp.albums_photos;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wordpress.lonelytripblog.simpleclientapp.R;
import com.wordpress.lonelytripblog.simpleclientapp.data.Photo;

import java.util.List;

/**
 * Adapter for photos activity.
 */

public class PhotosArrayAdapter extends ArrayAdapter<Photo> {

    public PhotosArrayAdapter(@NonNull Context context, List<Photo> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Photo photo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_item, parent, false);
        }
        ((TextView)convertView.findViewById(R.id.photo_title)).setText(photo.getTitle());
        Glide.with(getContext())
                .load(photo.getUrl())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into((ImageView)convertView.findViewById(R.id.image_in_list));
        return convertView;
    }
}
