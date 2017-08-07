package com.wordpress.lonelytripblog.simpleclientapp.all_users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wordpress.lonelytripblog.simpleclientapp.R;
import com.wordpress.lonelytripblog.simpleclientapp.data.User;

import java.util.List;

/**
 * Array adapter for ListView in All Users activity.
 */

public class AllUsersArrayAdapter extends ArrayAdapter<User> {

    public AllUsersArrayAdapter(@NonNull Context context, List<User> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        User user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.all_users_item, parent, false);
        }
        TextView userName = convertView.findViewById(R.id.user_name_in_users);
        TextView userEmail = convertView.findViewById(R.id.user_email_in_users);
        TextView userAddress = convertView.findViewById(R.id.user_address_in_users);
        userName.setText(user.getName());
        userEmail.setText(user.getEmail());
        userAddress.setText(user.getAddress());
        return convertView;
    }
}
