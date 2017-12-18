package com.margarita.vk_app.models.attachment.doc;

import com.google.gson.annotations.Expose;

import io.realm.RealmObject;

public class Size extends RealmObject {

    @Expose
    private String src;

    @Expose
    private int width;

    @Expose
    private int height;

    @Expose
    private String type;

    public String getSrc() {
        return src;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getType() {
        return type;
    }
}
