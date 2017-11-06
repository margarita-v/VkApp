
package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comments {

    @Expose
    private Integer count;

    @SerializedName("can_post")
    @Expose
    private Integer canPost;

    public Integer getCount() {
        return count;
    }

    public Integer getCanPost() {
        return canPost;
    }

}
