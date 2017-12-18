package com.margarita.vk_app.models.attachment.doc;

import com.google.gson.annotations.Expose;
import com.margarita.vk_app.models.attachment.video.Video;

import io.realm.RealmObject;

public class Preview extends RealmObject {

    @Expose
    private PhotoPreview photo;

    @Expose
    private Video video;

    public PhotoPreview getPhoto() {
        return photo;
    }

    public Video getVideo() {
        return video;
    }
}
