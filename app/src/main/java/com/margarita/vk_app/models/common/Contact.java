package com.margarita.vk_app.models.common;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Contact extends RealmObject {

    private static final String USER_ID = "user_id";
    public static final String PHOTO = "photo_100";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    @SerializedName(USER_ID)
    private int userId;

    private String desc;

    public int getUserId() {
        return userId;
    }

    public String getDesc() {
        return desc;
    }
}
