package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.models.attachment.ApiAttachment;
import com.margarita.vk_app.models.countable.Likes;
import com.margarita.vk_app.models.countable.Reposts;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CommentItem extends RealmObject {

    @PrimaryKey
    @Expose
    private int id;

    @SerializedName("from_id")
    @Expose
    private int senderId;

    @Expose
    private Place place;

    private String senderName;

    private String senderPhoto;

    @Expose
    private int date;

    @Expose
    private String text;

    @Expose
    private RealmList<ApiAttachment> attachments = new RealmList<>();

    private String attachmentsString;

    @Expose
    private Likes likes;

    @Expose
    private Reposts reposts;

    private boolean isFromTopic = false;

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public Integer getDate() {
        return date;
    }

    public Likes getLikes() {
        return likes;
    }

    public String getDisplayText() {
        return text;
    }

    public RealmList<ApiAttachment> getAttachments() {
        return attachments;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public String getDisplayAttachmentsString() {
        return attachmentsString;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSenderPhoto() {
        return senderPhoto;
    }

    public String getPhoto() {
        return senderPhoto;
    }

    public Place getPlace() {
        return place;
    }

    public boolean isFromTopic() {
        return isFromTopic;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setSenderPhoto(String senderPhoto) {
        this.senderPhoto = senderPhoto;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAttachments(RealmList<ApiAttachment> attachments) {
        this.attachments = attachments;
    }

    public void setAttachmentsString(String attachmentsString) {
        this.attachmentsString = attachmentsString;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public void setReposts(Reposts reposts) {
        this.reposts = reposts;
    }

    public void setFromTopic(boolean fromTopic) {
        isFromTopic = fromTopic;
    }
}
