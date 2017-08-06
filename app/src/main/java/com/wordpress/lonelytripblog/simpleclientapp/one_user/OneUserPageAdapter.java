package com.wordpress.lonelytripblog.simpleclientapp.one_user;

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

    public OneUserPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AlbumsFragment();
        } else {
            return new PostsFragment();
        }
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
