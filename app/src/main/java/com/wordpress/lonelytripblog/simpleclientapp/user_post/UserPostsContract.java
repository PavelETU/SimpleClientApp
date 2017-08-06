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
        boolean isEmptyViewSet();
        void setPosts(List<Post> posts);
    }
    interface Presenter {
        void loadUsers();
        void createPost(Post lastPostByUser);
    }
}
