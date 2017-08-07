package com.wordpress.lonelytripblog.simpleclientapp.user_post;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.lonelytripblog.simpleclientapp.R;
import com.wordpress.lonelytripblog.simpleclientapp.data.Post;
import com.wordpress.lonelytripblog.simpleclientapp.user_albums.UserAlbumsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment implements UserPostsContract.View, AddPostDialog.AddPostDialogListener {

    private TextView emptyView;
    private ProgressBar loadingIndicator;
    private UserPostsContract.Presenter mPresenter;
    private int userId;
    private PostsArrayAdapter mAdapter;
    private List<Post> mPosts;

    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        emptyView = view.findViewById(R.id.emptyView);
        loadingIndicator = view.findViewById(R.id.load_in_progress);
        mPosts = new ArrayList<>();
        mAdapter = new PostsArrayAdapter(mPosts);
        RecyclerView recyclerView = view.findViewById(R.id.albums_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
        ItemTouchHelper mIth = new ItemTouchHelper
                (new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        deletePost(position);
                    }
                });
        mIth.attachToRecyclerView(recyclerView);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddPostDialog();
            }
        });
        userId = getArguments().getInt("UserId");
        mPresenter = new PostsPresenter(this);
        return view;
    }

    public void deletePost(int position) {
        mPresenter.deletePost(mPosts.get(position));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadPosts(userId);
    }

    public void openAddPostDialog() {
        DialogFragment dialogFragment = new AddPostDialog(this);
        dialogFragment.show(getChildFragmentManager(), "AddPostDialog");
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
    public void setPosts(List<Post> posts) {
        mPosts.clear();
        mPosts.addAll(posts);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void succeedToDelete() {
        Toast.makeText(getActivity(), getString(R.string.delete_success), Toast.LENGTH_SHORT).show();
        mPresenter.loadPosts(userId);
    }

    @Override
    public void failedToDelete() {
        Toast.makeText(getActivity(), getString(R.string.delete_failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void succeedToPost() {
        Toast.makeText(getActivity(), getString(R.string.post_success), Toast.LENGTH_SHORT).show();
        mPresenter.loadPosts(userId);
    }

    @Override
    public void failedToPost() {
        Toast.makeText(getActivity(), getString(R.string.post_failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPositiveResult(String title, String body) {
        mPresenter.createPost(new Post(userId, 0, title, body));
    }

    @Override
    public void onNegativeResult() {
        // Do nothing
    }
}
