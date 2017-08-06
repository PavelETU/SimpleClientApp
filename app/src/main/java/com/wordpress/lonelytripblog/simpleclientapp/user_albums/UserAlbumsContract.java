package com.wordpress.lonelytripblog.simpleclientapp.user_albums;

import com.wordpress.lonelytripblog.simpleclientapp.data.Album;
import com.wordpress.lonelytripblog.simpleclientapp.data.User;

import java.util.List;

/**
 * OneUserContract for MVP architecture.
 */

public class UserAlbumsContract {
    interface View {
        void setLoadingState();
        void hideLoadingState();
        void showEmptyView();
        void hideEmptyView();
        boolean isEmptyViewSet();
        void setAlbums(List<Album> albums);
    }
    interface Presenter {
        void loadAlbums();
        void openAlbum(Album album);
    }
}
