
package com.margarita.vk_app.models.common;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WallItem {

    private String senderName;

    private String senderPhoto;

    @Expose
    private Integer id;

    @SerializedName("from_id")
    @Expose
    private Integer fromId;

    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;

    @Expose
    private Integer date;

    @SerializedName("marked_as_ads")
    @Expose
    private Integer markedAsAds;

    @SerializedName("post_type")
    @Expose
    private String postType;

    @Expose
    private String text;

    @SerializedName("can_pin")
    @Expose
    private Integer canPin;

    @Expose
    private List<Attachment> attachments = new ArrayList<>();

    @SerializedName("copy_history")
    @Expose
    private List<WallItem> copyHistory = new ArrayList<>();

    @SerializedName("post_source")
    @Expose
    private PostSource postSource;

    @Expose
    private Comments comments;

    @Expose
    private Likes likes;

    @Expose
    private Reposts reposts;

    @Expose
    private Views views;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromId() {
        return fromId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public Integer getDate() {
        return date;
    }

    public Integer getMarkedAsAds() {
        return markedAsAds;
    }

    public String getPostType() {
        return postType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCanPin() {
        return canPin;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public PostSource getPostSource() {
        return postSource;
    }

    public Comments getComments() {
        return comments;
    }

    public Likes getLikes() {
        return likes;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public Views getViews() {
        return views;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhoto() {
        return senderPhoto;
    }

    public void setSenderPhoto(String senderPhoto) {
        this.senderPhoto = senderPhoto;
    }

    /**
     * Check if the wall item contains repost
     * @return True if the wall item contains repost
     */
    public boolean hasSharedRepost() {
        return copyHistory.size() > 0;
    }

    public WallItem getSharedRepost() {
        return hasSharedRepost() ? copyHistory.get(0) : null;
    }

}
