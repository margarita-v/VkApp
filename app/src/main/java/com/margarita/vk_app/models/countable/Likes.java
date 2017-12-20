
package com.margarita.vk_app.models.countable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Likes extends RealmObject implements Countable {

    private static final int USER_LIKES_VALUE = 1;

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

    @Override
    public int getCount() {
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

    public boolean isUserLikes() {
        return userLikes == USER_LIKES_VALUE;
    }
}
