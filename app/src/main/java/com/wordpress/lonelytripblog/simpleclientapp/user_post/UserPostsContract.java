package com.wordpress.lonelytripblog.simpleclientapp.user_post;

import com.wordpress.lonelytripblog.simpleclientapp.data.Post;
import com.wordpress.lonelytripblog.simpleclientapp.data.User;

import java.util.List;

/**
 * OneUserContract for MVP architecture.
 */

public class UserPostsContract {
    interface View {
        void setLoadingState();
        void hideLoadingState();
        void showEmptyView();
        void hideEmptyView();
        boolean isEmptyViewShown();
        void setPosts(List<Post> posts);
        void succeedToDelete();
        void failedToDelete();
        void succeedToPost();
        void failedToPost();
    }
    interface Presenter {
        void loadPosts(int userId);
        void createPost(Post postToCreate);
        void deletePost(Post postToDelete);
    }
}
