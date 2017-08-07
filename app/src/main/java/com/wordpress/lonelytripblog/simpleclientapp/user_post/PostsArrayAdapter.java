package com.wordpress.lonelytripblog.simpleclientapp.user_post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wordpress.lonelytripblog.simpleclientapp.R;
import com.wordpress.lonelytripblog.simpleclientapp.data.Post;
import com.wordpress.lonelytripblog.simpleclientapp.data.User;

import java.util.List;

/**
 * Array adapter for ListView in All Users activity.
 */

public class PostsArrayAdapter extends RecyclerView.Adapter<PostsArrayAdapter.MyViewHolder> {

    private List<Post> posts;

    public PostsArrayAdapter(List<Post> posts) {
        this.posts = posts;
    }


    @Override
    public PostsArrayAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_post_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostsArrayAdapter.MyViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.postTitle.setText(post.getTitle());
        holder.postBody.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView postTitle;
        public TextView postBody;

        public MyViewHolder(View itemView) {
            super(itemView);
            postTitle = itemView.findViewById(R.id.single_post_title);
            postBody = itemView.findViewById(R.id.single_post_body);
        }
    }
}
