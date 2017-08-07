package com.wordpress.lonelytripblog.simpleclientapp.albums_photos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wordpress.lonelytripblog.simpleclientapp.R;

public class SinglePhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_photo);
        String url = getIntent().getExtras().getString(getString(R.string.photos_extra_photo_url));
        Glide.with(this)
                .load(url)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into((ImageView) findViewById(R.id.photo_out));
    }
}
