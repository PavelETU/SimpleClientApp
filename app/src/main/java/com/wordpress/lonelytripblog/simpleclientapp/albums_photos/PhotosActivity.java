package com.wordpress.lonelytripblog.simpleclientapp.albums_photos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wordpress.lonelytripblog.simpleclientapp.R;
import com.wordpress.lonelytripblog.simpleclientapp.data.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotosActivity extends AppCompatActivity implements AlbumsPhotosContract.View {

    private int albumId;
    private TextView emptyView;
    private ProgressBar loadingIndicator;
    private AlbumsPhotosContract.Presenter mPresenter;
    private ArrayAdapter<Photo> allPhotosArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        albumId = getIntent().getExtras().getInt(getString(R.string.albums_extra_album_id));
        emptyView = (TextView) findViewById(R.id.emptyView);
        loadingIndicator = (ProgressBar) findViewById(R.id.load_in_progress);
        ListView listView = (ListView) findViewById(R.id.photos_list);
        allPhotosArrayAdapter = new PhotosArrayAdapter(this, new ArrayList<Photo>());
        listView.setAdapter(allPhotosArrayAdapter);
        mPresenter = new AlbumsPhotosPresenter(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mPresenter.openPhoto(allPhotosArrayAdapter.getItem(position));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadPhotos(albumId);
    }

    @Override
    public void setLoadingState() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingState() {
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public boolean isEmptyViewShown() {
        return emptyView.isShown();
    }

    @Override
    public void setPhotos(List<Photo> photos) {
        allPhotosArrayAdapter.clear();
        allPhotosArrayAdapter.addAll(photos);
    }

    @Override
    public void openPhoto(String url) {
        Intent intent = new Intent(this, SinglePhotoActivity.class);
        intent.putExtra(getString(R.string.photos_extra_photo_url), url);
        startActivity(intent);
    }
}
