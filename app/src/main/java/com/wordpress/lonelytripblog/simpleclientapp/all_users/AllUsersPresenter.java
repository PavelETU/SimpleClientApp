package com.wordpress.lonelytripblog.simpleclientapp.all_users;

import android.content.Intent;

import com.wordpress.lonelytripblog.simpleclientapp.all_users.AllUsersContract.*;
import com.wordpress.lonelytripblog.simpleclientapp.data.Provider;
import com.wordpress.lonelytripblog.simpleclientapp.data.Repository;
import com.wordpress.lonelytripblog.simpleclientapp.data.User;

import java.util.List;

/**
 * Presenter for all users.
 */

public class AllUsersPresenter implements Presenter {

    private View mView;


    public AllUsersPresenter(View view) {
        mView = view;
    }

    @Override
    public void loadUsers() {
        mView.setLoadingState();
        Provider.getInstance().getAllUsers(new Repository.LoadUsersCallback() {
            @Override
            public void onUsersLoaded(List<User> users) {
                mView.hideLoadingState();
                if (users == null || users.size() == 0) {
                    mView.showEmptyView();
                } else {
                    if (mView.isEmptyViewShown()) {
                        mView.hideEmptyView();
                    }
                    mView.setUsers(users);
                }
            }
        });
    }

    @Override
    public void openUser(User user) {
        String description = "User name is "+user.getName();
        mView.openUserById(description, user.getId());
    }
}
