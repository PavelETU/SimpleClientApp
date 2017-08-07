package com.wordpress.lonelytripblog.simpleclientapp.user_albums;

import com.wordpress.lonelytripblog.simpleclientapp.data.Album;
import com.wordpress.lonelytripblog.simpleclientapp.data.Provider;
import com.wordpress.lonelytripblog.simpleclientapp.data.Repository;
import com.wordpress.lonelytripblog.simpleclientapp.user_albums.UserAlbumsContract.Presenter;

import java.util.List;

/**
 * Concrete presenter for albums fragment.
 */

public class AlbumsPresenter implements Presenter {

    private UserAlbumsContract.View mView;

    public AlbumsPresenter(UserAlbumsContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void loadAlbums(int userId) {
        mView.setLoadingState();
        Provider.getInstance().getAlbumsByUser(userId, new Repository.LoadAlbumsByUserCallback() {
            @Override
            public void onAlbumsLoaded(List<Album> albums) {
                mView.hideLoadingState();
                if (albums == null || albums.size() == 0) {
                    mView.showEmptyView();
                } else {
                    if (mView.isEmptyViewShown()) {
                        mView.hideEmptyView();
                    }
                    mView.setAlbums(albums);
                }
            }
        });
    }

    @Override
    public void openAlbum(Album album) {
        mView.openAlbumById(album.getId());
    }
}
