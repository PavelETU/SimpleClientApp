package com.wordpress.lonelytripblog.simpleclientapp.user_albums;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wordpress.lonelytripblog.simpleclientapp.R;
import com.wordpress.lonelytripblog.simpleclientapp.albums_photos.PhotosActivity;
import com.wordpress.lonelytripblog.simpleclientapp.data.Album;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsFragment extends Fragment implements UserAlbumsContract.View {

    private TextView emptyView;
    private ProgressBar loadingIndicator;
    private UserAlbumsContract.Presenter mPresenter;
    private int userId;
    private ArrayAdapter<Album> mAdapter;

    public AlbumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_albums, container, false);
        emptyView = view.findViewById(R.id.emptyView);
        loadingIndicator = view.findViewById(R.id.load_in_progress);
        mAdapter = new ArrayAdapter<>(getActivity(),
                                                android.R.layout.simple_list_item_1,
                                                new ArrayList<Album>());
        ListView listView = (ListView) view.findViewById(R.id.albums_list);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mPresenter.openAlbum(mAdapter.getItem(position));
            }
        });
        userId = getArguments().getInt("UserId");
        mPresenter = new AlbumsPresenter(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.loadAlbums(userId);
        }
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
    public void setAlbums(List<Album> albums) {
        mAdapter.clear();
        mAdapter.addAll(albums);
    }

    @Override
    public void openAlbumById(int albumId) {
        Intent intent = new Intent(getActivity(), PhotosActivity.class);
        intent.putExtra(getString(R.string.albums_extra_album_id), albumId);
        startActivity(intent);
    }
}
