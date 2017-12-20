
package com.margarita.vk_app.models.countable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Reposts extends RealmObject implements Countable {

    @Expose
    private Integer count;

    @SerializedName("user_reposted")
    @Expose
    private Integer userReposted;

    @Override
    public int getCount() {
        return count;
    }

    public Integer getUserReposted() {
        return userReposted;
    }
}
