
package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Likes extends RealmObject {

    @Expose
    private Integer count;

    @SerializedName("user_likes")
    @Expose
    private Integer userLikes;

    @SerializedName("can_like")
    @Expose
    private Integer canLike;

    @SerializedName("can_publish")
    @Expose
    private Integer canPublish;

    public Integer getCount() {
        return count;
    }

    public Integer getUserLikes() {
        return userLikes;
    }

    public Integer getCanLike() {
        return canLike;
    }

    public Integer getCanPublish() {
        return canPublish;
    }
}
