package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;
import com.vk.sdk.api.model.Identifiable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Topic extends RealmObject implements Identifiable {

    @PrimaryKey
    @Expose
    private int id;

    @Expose
    private String title;

    @Expose
    private int comments;

    private int groupId;

    @Override
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getComments() {
        return comments;
    }

    public int getGroupId() {
        return groupId;
    }
}
