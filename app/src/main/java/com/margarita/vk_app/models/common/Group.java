
package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.models.Owner;

public class Group implements Owner {

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

}
