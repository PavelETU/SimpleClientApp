package com.wordpress.lonelytripblog.simpleclientapp.add_post;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.wordpress.lonelytripblog.simpleclientapp.R;

/**
 * Add post dialog.
 */

public class AddPostDialog extends DialogFragment {
    public interface AddPostDialogListener {
        void onPositiveResult(String title, String body);
        void onNegativeResult();
    }

    private AddPostDialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (AddPostDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(getActivity().getLayoutInflater().inflate(R.layout.add_post_dialog, null))
                .setPositiveButton(R.string.send, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // TODO handle text
                        mListener.onPositiveResult(null, null);
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
