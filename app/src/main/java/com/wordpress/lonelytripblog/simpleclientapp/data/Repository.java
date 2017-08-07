package com.wordpress.lonelytripblog.simpleclientapp.data;

import java.util.List;

/**
 * Model interface in MVP pattern.
 */

public interface Repository {
    interface LoadUsersCallback {
        void onUsersLoaded(List<User> users);
    }
    interface LoadAlbumsByUserCallback {
        void onAlbumsLoaded(List<Album> albums);
    }
    interface LoadPostsByUserCallback {
        void onPostsLoaded(List<Post> posts);
    }
    interface LoadPhotosByAlbumCallback {
        void onImagesLoaded(List<Photo> photos);
    }
    interface CreatePostCallback {
        void onSuccess();
        void onFail();
    }
    interface DeletePostCallback {
        void onSuccess();
        void onFail();
    }
    void getAllUsers(LoadUsersCallback callback);
    void getAlbumsByUser(int userId, LoadAlbumsByUserCallback callback);
    void getPostsByUser(int userId, LoadPostsByUserCallback callback);
    void getPhotosByAlbum(int albumId, LoadPhotosByAlbumCallback callback);
    void postPost(String title, String body, int userId, CreatePostCallback callback);
    void deletePost(int postId, DeletePostCallback callback);
}
