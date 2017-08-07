package com.wordpress.lonelytripblog.simpleclientapp.user_post;

import com.wordpress.lonelytripblog.simpleclientapp.data.Post;
import com.wordpress.lonelytripblog.simpleclientapp.data.Provider;
import com.wordpress.lonelytripblog.simpleclientapp.data.Repository;

import java.util.List;

/**
 * Presenter for posts fragment.
 */

public class PostsPresenter implements UserPostsContract.Presenter {
    private UserPostsContract.View mView;

    public PostsPresenter(UserPostsContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void loadPosts(int userId) {
        mView.setLoadingState();
        Provider.getInstance().getPostsByUser(userId, new Repository.LoadPostsByUserCallback() {
            @Override
            public void onPostsLoaded(List<Post> posts) {
                mView.hideLoadingState();
                if (posts == null || posts.size() == 0) {
                    mView.showEmptyView();
                } else {
                    if (mView.isEmptyViewShown()) {
                        mView.hideEmptyView();
                    }
                    mView.setPosts(posts);
                }
            }
        });
    }

    @Override
    public void createPost(Post postToCreate) {
        Provider.getInstance().postPost(postToCreate.getTitle(), postToCreate.getBody(), postToCreate.getUserId(), new Repository.CreatePostCallback() {
            @Override
            public void onSuccess() {
                mView.succeedToPost();
            }
            @Override
            public void onFail() {
                mView.failedToPost();
            }
        });
    }

    @Override
    public void deletePost(Post postToDelete) {
        Provider.getInstance().deletePost(postToDelete.getId(), new Repository.DeletePostCallback() {
            @Override
            public void onSuccess() {
                mView.succeedToDelete();
            }

            @Override
            public void onFail() {
                mView.failedToDelete();
            }
        });
    }
}
