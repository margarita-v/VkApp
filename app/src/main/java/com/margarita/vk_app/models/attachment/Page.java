package com.margarita.vk_app.models.attachment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.VKAttachments;

import io.realm.RealmObject;

public class Page extends RealmObject implements Attachment {

    @Expose
    private int id;

    @Expose
    private String title;

    @SerializedName("view_url")
    @Expose
    private String url;

    @Override
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String getType() {
        return VKAttachments.TYPE_WIKI_PAGE;
    }

    @Override
    public String getIconFont() {
        return "";
    }
}
