package com.wordpress.lonelytripblog.simpleclientapp.all_users;

import com.wordpress.lonelytripblog.simpleclientapp.data.User;

import java.util.List;

/**
 * OneUserContract for MVP architecture.
 */

public class AllUsersContract {
    interface View {
        void setLoadingState();
        void hideLoadingState();
        void showEmptyView();
        void hideEmptyView();
        boolean isEmptyViewShown();
        void setUsers(List<User> users);
        void openUserById(String userDescription, int userId);
    }
    interface Presenter {
        void loadUsers();
        void openUser(User user);
    }
}
