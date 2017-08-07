package com.wordpress.lonelytripblog.simpleclientapp.one_user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wordpress.lonelytripblog.simpleclientapp.user_albums.AlbumsFragment;
import com.wordpress.lonelytripblog.simpleclientapp.user_post.PostsFragment;

/**
 * Page adapter for ViewPager.
 */

public class OneUserPageAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 2;
    private String titles[] = new String[]{"Albums", "Posts"};
    private int userId;

    public OneUserPageAdapter(FragmentManager fm, int userId) {
        super(fm);
        this.userId = userId;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("UserId", userId);
        Fragment fragment;
        if (position == 0) {
            fragment = new AlbumsFragment();
        } else {
            fragment = new PostsFragment();
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
