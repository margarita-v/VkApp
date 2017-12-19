package com.margarita.vk_app.ui.holder.attachment.doc;

import android.view.View;
import android.widget.ImageView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.attachment.doc.DocImageAttachment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DocImageAttachmentHolder extends BaseDocHolder<DocImageAttachment> {

    @BindView(R.id.ivAttachmentImage)
    private ImageView ivAttachmentImage;

    public DocImageAttachmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(DocImageAttachment docImageAttachment) {
        super.bindViewHolder(docImageAttachment);
        setOnClickListener(docImageAttachment);
        loadImage(docImageAttachment.getImage(), ivAttachmentImage);
    }

    @Override
    public void unbindViewHolder() {
        super.unbindViewHolder();
        clearImageView(ivAttachmentImage);
    }
}
