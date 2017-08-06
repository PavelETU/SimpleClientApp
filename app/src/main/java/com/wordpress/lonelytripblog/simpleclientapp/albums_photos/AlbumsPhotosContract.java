package com.wordpress.lonelytripblog.simpleclientapp.albums_photos;

import com.wordpress.lonelytripblog.simpleclientapp.data.Photo;
import com.wordpress.lonelytripblog.simpleclientapp.data.Post;

import java.util.List;

/**
 * OneUserContract for MVP architecture.
 */

public class AlbumsPhotosContract {
    interface View {
        void setLoadingState();
        void hideLoadingState();
        void showEmptyView();
        void hideEmptyView();
        boolean isEmptyViewSet();
        void setPhotos(List<Photo> photos);
    }
    interface Presenter {
        void loadPhotos(int albumId);
        void openPhoto(Photo photoToOpen);
    }
}
