package com.margarita.vk_app.models.attachment.doc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.models.attachment.Attachment;
import com.vk.sdk.api.model.VKAttachments;

import io.realm.RealmObject;

public class VkDocument extends RealmObject implements Attachment {

    public static final String ICON_FONT = new String(new char[]{0xE02C});

    @Expose
    private int id;

    @SerializedName("owner_id")
    @Expose
    private int ownerId;

    @Expose
    private String title;

    @Expose
    private int size;

    @Expose
    private String ext;

    @Expose
    private String url;

    @Expose
    private int date;

    @SerializedName("type")
    @Expose
    private int docType;

    @SerializedName("access_key")
    @Expose
    private String accessKey;

    @Expose
    private Preview preview;

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public String getExt() {
        return ext;
    }

    public String getUrl() {
        return url;
    }

    public int getDate() {
        return date;
    }

    public int getDocType() {
        return docType;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public Preview getPreview() {
        return preview;
    }

    @Override
    public String getType() {
        return VKAttachments.TYPE_DOC;
    }

    @Override
    public String getIconFont() {
        return ICON_FONT;
    }
}
