package com.margarita.vk_app.models.attachment.video;

import io.realm.RealmObject;

public class Files extends RealmObject {

    private String external;

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }
}
