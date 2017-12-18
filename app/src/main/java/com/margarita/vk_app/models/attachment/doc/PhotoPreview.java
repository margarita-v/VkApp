package com.margarita.vk_app.models.attachment.doc;

import io.realm.RealmList;
import io.realm.RealmObject;

public class PhotoPreview extends RealmObject {

    private RealmList<Size> sizes;

    public RealmList<Size> getSizes() {
        return sizes;
    }
}
