
package com.margarita.vk_app.models.common;

import com.google.gson.annotations.Expose;

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
