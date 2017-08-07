package com.wordpress.lonelytripblog.simpleclientapp.one_user;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.wordpress.lonelytripblog.simpleclientapp.R;

public class OneUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int userId = getIntent().getExtras().getInt(getString(R.string.one_user_extra_message));
        String userDescription = getIntent().getExtras().getString(getString(R.string.one_user_extra_description));
        setContentView(R.layout.activity_one_user);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new OneUserPageAdapter(getSupportFragmentManager(), userId));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        ((TextView)findViewById(R.id.user_description)).setText(userDescription);
    }
}
