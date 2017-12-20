
package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.models.attachment.ApiAttachment;
import com.margarita.vk_app.models.countable.Comments;
import com.margarita.vk_app.models.countable.Likes;
import com.margarita.vk_app.models.countable.Reposts;
import com.margarita.vk_app.models.countable.Views;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WallItem extends RealmObject {

    private String attachmentsString;

    private String senderName;

    private String senderPhoto;

    @PrimaryKey
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
    private RealmList<ApiAttachment> attachments = new RealmList<>();

    @SerializedName("copy_history")
    @Expose
    private RealmList<WallItem> copyHistory = new RealmList<>();

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

    public RealmList<ApiAttachment> getAttachments() {
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

    public String getAttachmentsString() {
        return attachmentsString;
    }

    public void setAttachmentsString(String attachmentsString) {
        this.attachmentsString = attachmentsString;
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
