package com.margarita.vk_app.models.view.attachment;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.attachment.Photo;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.attachment.ImageAttachmentHolder;

public class ImageAttachment extends BaseAttachment {

    private boolean needClick = true;

    private static final String IMAGE_TITLE = "Image";

    public ImageAttachment(String url) {
        super(url);
        this.needClick = false;
    }

    public ImageAttachment(Photo photo) {
        super(photo.getPhoto604());
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentImage;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new ImageAttachmentHolder(view);
    }

    @Override
    protected String getDefaultTitle() {
        return IMAGE_TITLE;
    }

    public boolean isNeedClick() {
        return needClick;
    }
}
