package com.margarita.vk_app.models.view.attachment.link;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.attachment.Link;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.attachment.link.LinkAttachmentHolder;

public class LinkAttachment extends BaseLinkAttachment {

    public LinkAttachment(Link link) {
        super(link);
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new LinkAttachmentHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentLink;
    }
}
