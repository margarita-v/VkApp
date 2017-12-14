
package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.models.Owner;
import com.margarita.vk_app.models.attachment.Link;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Group extends RealmObject implements Owner {

    @Expose
    private Integer id;

    @Expose
    private String name;

    @SerializedName("screen_name")
    @Expose
    private String screenName;

    @SerializedName("is_closed")
    @Expose
    private Integer isClosed;

    @Expose
    private String type;

    @SerializedName("is_admin")
    @Expose
    private Integer isAdmin;

    @SerializedName("is_member")
    @Expose
    private Integer isMember;

    @SerializedName("photo_50")
    @Expose
    private String photo50;

    @SerializedName("photo_100")
    @Expose
    private String photo100;

    @SerializedName("photo_200")
    @Expose
    private String photo200;

    @Expose
    private String status;

    @Expose
    private String description;

    @Expose
    private String site;

    @Expose
    private RealmList<Link> links;

    @Expose
    private RealmList<Contact> contacts;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public Integer getIsClosed() {
        return isClosed;
    }

    public String getType() {
        return type;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public Integer getIsMember() {
        return isMember;
    }

    public String getPhoto50() {
        return photo50;
    }

    public String getPhoto100() {
        return photo100;
    }

    public String getPhoto200() {
        return photo200;
    }

    @Override
    public String getFullName() {
        return name;
    }

    @Override
    public String getPhoto() {
        return photo100;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getSite() {
        return site;
    }

    public RealmList<Link> getLinks() {
        return links;
    }

    public RealmList<Contact> getContacts() {
        return contacts;
    }
}
