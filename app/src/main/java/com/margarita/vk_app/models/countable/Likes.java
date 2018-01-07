
package com.margarita.vk_app.models.countable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Likes extends RealmObject implements BaseAction {

    /**
     * Count of users which had liked the post
     */
    @Expose
    private Integer count;

    /**
     * A mark of a presence of the current user's like.
     * 1 - user liked the post, 0 otherwise.
     */
    @SerializedName("user_likes")
    @Expose
    private Integer userLikes;

    /**
     * A mark about the current user's ability to add a like
     * 1 - user can like the post, 0 otherwise
     */
    @SerializedName("can_like")
    @Expose
    private Integer canLike;

    /**
     * A mark about the current user's ability to do a repost
     * 1 - user can do a repost, 0 otherwise
     */
    @SerializedName("can_publish")
    @Expose
    private Integer canPublish;

    @Override
    public int getCount() {
        return count;
    }

    public boolean canLike() {
        return checkAction(canLike);
    }

    public boolean canPublish() {
        return checkAction(canPublish);
    }

    @Override
    public boolean isUserPerformed() {
        return checkAction(userLikes);
    }
}
