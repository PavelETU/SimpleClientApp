package com.wordpress.lonelytripblog.simpleclientapp.all_users;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wordpress.lonelytripblog.simpleclientapp.R;
import com.wordpress.lonelytripblog.simpleclientapp.data.User;
import com.wordpress.lonelytripblog.simpleclientapp.one_user.OneUser;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity implements AllUsersContract.View {

    private TextView emptyView;
    private ProgressBar loadingIndicator;
    private AllUsersContract.Presenter mPresenter;
    private ArrayAdapter<User> allUsersArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        emptyView = (TextView) findViewById(R.id.emptyView);
        loadingIndicator = (ProgressBar) findViewById(R.id.load_in_progress);
        ListView listView = (ListView) findViewById(R.id.all_users_list);
        allUsersArrayAdapter = new AllUsersArrayAdapter(this, new ArrayList<User>());
        listView.setAdapter(allUsersArrayAdapter);
        mPresenter = new AllUsersPresenter(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mPresenter.openUser(allUsersArrayAdapter.getItem(position));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadUsers();
    }

    @Override
    public void setLoadingState() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingState() {
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public boolean isEmptyViewShown() {
        return emptyView.isShown();
    }

    @Override
    public void setUsers(List<User> users) {
        allUsersArrayAdapter.clear();
        allUsersArrayAdapter.addAll(users);
    }

    @Override
    public void openUserById(String description, int userId) {
        Intent intent = new Intent(this, OneUser.class);
        intent.putExtra(getString(R.string.one_user_extra_description), description);
        intent.putExtra(getString(R.string.one_user_extra_message), userId);
        startActivity(intent);
    }
}
