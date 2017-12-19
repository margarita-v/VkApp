package com.margarita.vk_app.models.view.attachment;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.attachment.Page;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.attachment.PageAttachmentHolder;

public class PageAttachment extends BaseAttachment {

    public PageAttachment(Page page) {
        super(page.getTitle(), page.getUrl());
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new PageAttachmentHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentPage;
    }
}
