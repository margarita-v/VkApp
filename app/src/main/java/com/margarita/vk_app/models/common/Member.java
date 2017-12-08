package com.margarita.vk_app.models.common;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Member extends RealmObject {

    //region String constants for fields names
    private static final String GROUP_ID = "group_id";
    private static final String PHOTO = "photo_100";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    //endregion

    @PrimaryKey
    private int id;

    @SerializedName(GROUP_ID)
    private int groupId;

    @SerializedName(PHOTO)
    private String photo;

    @SerializedName(FIRST_NAME)
    private String firstName;

    @SerializedName(LAST_NAME)
    private String lastName;

    private String fullName;

    public Member() {

    }

    public Member(Profile profile) {
        this.id = profile.getId();
        this.photo = profile.getPhoto();
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.fullName = profile.getFullName();
    }

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getPhoto() {
        return photo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }
}
