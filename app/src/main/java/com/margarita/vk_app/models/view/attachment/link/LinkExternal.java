package com.margarita.vk_app.models.view.attachment.link;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.attachment.Link;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.attachment.link.LinkExternalHolder;

public class LinkExternal extends BaseLinkAttachment {

    private String imageUrl;

    public LinkExternal(Link link) {
        super(link);
        this.imageUrl = link.getPhoto().getPhoto604();
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new LinkExternalHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentLinkExternal;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
