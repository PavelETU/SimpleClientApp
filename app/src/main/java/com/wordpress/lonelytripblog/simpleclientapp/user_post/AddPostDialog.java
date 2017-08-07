package com.wordpress.lonelytripblog.simpleclientapp.user_post;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.wordpress.lonelytripblog.simpleclientapp.R;

/**
 * Add post dialog.
 */

public class AddPostDialog extends DialogFragment {


    public AddPostDialog(AddPostDialogListener mListener) {
        this.mListener = mListener;
    }

    public AddPostDialog() {
    }

    public interface AddPostDialogListener {
        void onPositiveResult(String title, String body);
        void onNegativeResult();
    }

    private AddPostDialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.add_post_dialog, null);
        final EditText title = dialogView.findViewById(R.id.post_title);
        final EditText body = dialogView.findViewById(R.id.post_body);
        builder.setView(dialogView)
                .setPositiveButton(R.string.send, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!title.getText().toString().equals("") || !body.getText().toString().equals("")) {
                            mListener.onPositiveResult(title.getText().toString(), body.getText().toString());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onNegativeResult();
                    }
                });
        return builder.create();
    }
}
