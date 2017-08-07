package com.wordpress.lonelytripblog.simpleclientapp.albums_photos;

import com.wordpress.lonelytripblog.simpleclientapp.data.Photo;
import com.wordpress.lonelytripblog.simpleclientapp.data.Provider;
import com.wordpress.lonelytripblog.simpleclientapp.data.Repository;

import java.util.List;

/**
 * Presenter for albums activity.
 */

public class AlbumsPhotosPresenter implements AlbumsPhotosContract.Presenter {

    private AlbumsPhotosContract.View mView;

    public AlbumsPhotosPresenter(AlbumsPhotosContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void loadPhotos(int albumId) {
        Provider.getInstance().getPhotosByAlbum(albumId, new Repository.LoadPhotosByAlbumCallback() {
            @Override
            public void onImagesLoaded(List<Photo> photos) {
                mView.hideLoadingState();
                if (photos == null || photos.size() == 0) {
                    mView.showEmptyView();
                } else {
                    if (mView.isEmptyViewShown()) {
                        mView.hideEmptyView();
                    }
                    mView.setPhotos(photos);
                }
            }
        });
    }

    @Override
    public void openPhoto(Photo photoToOpen) {
        mView.openPhoto(photoToOpen.getUrl());
    }
}
