
package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Views extends RealmObject {

    @Expose
    private Integer count;

    public Integer getCount() {
        return count;
    }

}
