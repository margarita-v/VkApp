
package com.margarita.vk_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment {

    @Expose
    private String type;

    @Expose
    private Photo photo;

    public String getType() {
        return type;
    }

    public Photo getPhoto() {
        return photo;
    }

}
