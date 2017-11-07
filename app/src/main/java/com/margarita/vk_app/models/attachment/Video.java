package com.margarita.vk_app.models.attachment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.VKAttachments;

public class Video implements Attachment {

    private static final String ICON_FONT = new String(new char[]{0xE02C});

    @Expose
    private int id;

    @SerializedName("owner_id")
    @Expose
    private int ownerId;

    @Expose
    private String title;

    @Expose
    private int duration;

    @Expose
    private String description;

    @Expose
    private int date;

    @Expose
    private int views;

    @Expose
    private int comments;

    @SerializedName("photo_130")
    @Expose
    private String photo130;

    @SerializedName("photo_320")
    @Expose
    private String photo320;

    @SerializedName("photo_800")
    @Expose
    private String photo800;

    @Expose
    private String player;

    @SerializedName("access_key")
    @Expose
    private String accessKey;

    @SerializedName("can_add")
    @Expose
    private int canAdd;

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public int getDate() {
        return date;
    }

    public int getViews() {
        return views;
    }

    public int getComments() {
        return comments;
    }

    public String getPhoto130() {
        return photo130;
    }

    public String getPhoto320() {
        return photo320;
    }

    public String getPhoto800() {
        return photo800;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public int getCanAdd() {
        return canAdd;
    }

    public String getPlayer() {
        return player;
    }

    @Override
    public String getType() {
        return VKAttachments.TYPE_VIDEO;
    }

    @Override
    public String getIconFont() {
        return ICON_FONT;
    }
}