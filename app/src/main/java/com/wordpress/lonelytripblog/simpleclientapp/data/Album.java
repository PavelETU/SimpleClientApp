package com.wordpress.lonelytripblog.simpleclientapp.data;

/**
 * Album contract.
 */

public class Album {
    private String title;
    private int id;

    @Override
    public String toString() {
        return "Album title is " + title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
