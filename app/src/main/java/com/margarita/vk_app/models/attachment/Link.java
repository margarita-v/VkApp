package com.margarita.vk_app.models.attachment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.VKAttachments;

public class Link implements Attachment {

    @Expose
    private String url;

    @Expose
    private String title;

    @Expose
    private String name;

    @Expose
    private String caption;

    @Expose
    private String description;

    @Expose
    private Photo photo;

    @SerializedName("is_external")
    @Expose
    private int isExternal;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public Photo getPhoto() {
        return photo;
    }

    public int isExternal() {
        return isExternal;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getType() {
        return VKAttachments.TYPE_LINK;
    }

    @Override
    public String getIconFont() {
        return "0xE250";
    }
}
