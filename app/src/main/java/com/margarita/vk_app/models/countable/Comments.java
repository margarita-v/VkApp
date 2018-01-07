
package com.margarita.vk_app.models.countable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Comments extends RealmObject implements BaseAction {

    @Expose
    private Integer count;

    @SerializedName("can_post")
    @Expose
    private Integer canPost;

    @Override
    public int getCount() {
        return count;
    }

    public boolean canPost() {
        return checkAction(canPost);
    }
}
