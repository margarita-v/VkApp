package com.margarita.vk_app.models.attachment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.VKAttachments;

public class Photo implements Attachment {

    @Expose
    private Integer id;

    @SerializedName("album_id")
    @Expose
    private Integer albumId;

    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;

    @SerializedName("user_id")
    @Expose
    private Integer userId;

    @SerializedName("photo_75")
    @Expose
    private String photo75;

    @SerializedName("photo_130")
    @Expose
    private String photo130;

    @SerializedName("photo_604")
    @Expose
    private String photo604;

    @SerializedName("photo_807")
    @Expose
    private String photo807;

    @SerializedName("photo_1280")
    @Expose
    private String photo1280;

    @Expose
    private Integer width;

    @Expose
    private Integer height;

    @Expose
    private String text;

    @Expose
    private Integer date;

    @SerializedName("access_key")
    @Expose
    private String accessKey;

    public int getId() {
        return id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getPhoto75() {
        return photo75;
    }

    public String getPhoto130() {
        return photo130;
    }

    public String getPhoto604() {
        return photo604;
    }

    public String getPhoto807() {
        return photo807;
    }

    public String getPhoto1280() {
        return photo1280;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getDate() {
        return date;
    }

    public String getAccessKey() {
        return accessKey;
    }

    @Override
    public String getType() {
        return VKAttachments.TYPE_PHOTO;
    }

    @Override
    public String getIconFont() {
        return "0xE251";
    }
}
