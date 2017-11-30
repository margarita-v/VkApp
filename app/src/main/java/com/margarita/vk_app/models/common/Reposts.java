
package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Reposts extends RealmObject{

    @Expose
    private Integer count;

    @SerializedName("user_reposted")
    @Expose
    private Integer userReposted;

    public Integer getCount() {
        return count;
    }

    public Integer getUserReposted() {
        return userReposted;
    }

}
