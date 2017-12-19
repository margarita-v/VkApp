package com.margarita.vk_app.ui.holder.attachment.link;

import android.view.View;
import android.widget.ImageView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.attachment.link.LinkExternal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinkExternalHolder extends BaseLinkHolder<LinkExternal> {

    @BindView(R.id.ivAttachmentImage)
    ImageView ivAttachmentImage;

    public LinkExternalHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(LinkExternal attachment) {
        super.bindViewHolder(attachment);
        loadImage(attachment.getImageUrl(), ivAttachmentImage);
    }

    @Override
    public void unbindViewHolder() {
        super.unbindViewHolder();
        clearImageView(ivAttachmentImage);
    }
}
