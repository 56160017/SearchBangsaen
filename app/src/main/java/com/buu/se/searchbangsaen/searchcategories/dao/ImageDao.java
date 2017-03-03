package com.buu.se.searchbangsaen.searchcategories.dao;

/**
 * Created by Dell on 27/02/2560.
 */

public class ImageDao {
    private int id;
    private String url;

    public ImageDao(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
