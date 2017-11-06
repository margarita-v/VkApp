package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.models.Owner;

public class Profile implements Owner {

    @Expose
    private int id;

    @SerializedName("photo_50")
    @Expose
    private String photo50;

    @SerializedName("photo_100")
    @Expose
    private String photo100;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @Expose
    private int sex;

    @SerializedName("screen_name")
    @Expose
    private String screenName;

    @Expose
    private int online;

    @Expose
    private int hidden;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSex() {
        return sex;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getPhoto50() {
        return photo50;
    }

    public int getOnline() {
        return online;
    }

    public int getHidden() {
        return hidden;
    }

    public boolean isGroup() {
        return false;
    }

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getPhoto() {
        return photo100;
    }

    public String getDisplayProfilePhoto() {
        return photo100;
    }

}
