package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Member extends RealmObject {

    //region String constants for fields names
    private static final String PHOTO = "photo_100";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    //endregion

    private static final char SPACE = ' ';

    @PrimaryKey
    @Expose
    private int id;

    @SerializedName(VKApiConst.GROUP_ID)
    @Expose
    private int groupId;

    @SerializedName(PHOTO)
    @Expose
    private String photo;

    @SerializedName(FIRST_NAME)
    @Expose
    private String firstName;

    @SerializedName(LAST_NAME)
    @Expose
    private String lastName;

    public Member() {

    }

    public Member(Profile profile) {
        this.id = profile.getId();
        this.photo = profile.getPhoto();
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
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

    public String getFullName() {
        return firstName + SPACE + lastName;
    }
}
