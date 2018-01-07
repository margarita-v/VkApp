
package com.margarita.vk_app.models.countable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Reposts extends RealmObject implements Countable {

    /**
     * Flag which shows if the current user had reposted some post
     */
    private static final int USER_REPOSTED_VALUE = 1;

    @Expose
    private Integer count;

    @SerializedName("user_reposted")
    @Expose
    private Integer userReposted;

    @Override
    public int getCount() {
        return count;
    }

    public boolean isUserReposted() {
        return userReposted == USER_REPOSTED_VALUE;
    }
}
