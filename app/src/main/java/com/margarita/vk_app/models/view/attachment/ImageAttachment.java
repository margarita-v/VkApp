package com.margarita.vk_app.models.view.attachment;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.attachment.Photo;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.attachment.ImageAttachmentHolder;

public class ImageAttachment extends BaseViewModel {

    private String mPhotoUrl;
    private boolean needClick = true;

    public ImageAttachment(String url) {
        this.mPhotoUrl = url;
        this.needClick = false;
    }

    public ImageAttachment(Photo photo) {
        this.mPhotoUrl = photo.getPhoto604();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentImage;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new ImageAttachmentHolder(view);
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public boolean isNeedClick() {
        return needClick;
    }
}
