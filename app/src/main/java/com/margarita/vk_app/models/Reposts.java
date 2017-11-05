
package com.margarita.vk_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reposts {

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
