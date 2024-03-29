package com.margarita.vk_app.models.attachment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.VKAttachments;

import io.realm.RealmObject;

public class Audio extends RealmObject implements Attachment {

    private static final String ICON_FONT = new String(new char[]{0xE310});

    @Expose
    private int id;

    @SerializedName("owner_id")
    @Expose
    private int ownerId;

    @Expose
    private String artist;

    @Expose
    private String title;

    @Expose
    private int duration;

    @Expose
    private int date;

    @Expose
    private String url;

    @SerializedName("lyrics_id")
    @Expose
    private int lyricsId;

    @SerializedName("genre_id")
    @Expose
    private int genreId;

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public int getLyricsId() {
        return lyricsId;
    }

    public int getGenreId() {
        return genreId;
    }

    @Override
    public String getType() {
        return VKAttachments.TYPE_AUDIO;
    }

    @Override
    public String getIconFont() {
        return ICON_FONT;
    }
}
