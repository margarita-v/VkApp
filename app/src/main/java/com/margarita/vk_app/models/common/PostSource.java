
package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class PostSource extends RealmObject {

    @Expose
    private String type;

    public String getType() {
        return type;
    }

}
