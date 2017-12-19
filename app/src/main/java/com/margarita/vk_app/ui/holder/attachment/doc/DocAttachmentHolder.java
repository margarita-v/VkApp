package com.margarita.vk_app.ui.holder.attachment.doc;

import android.view.View;

import com.margarita.vk_app.models.view.attachment.doc.BaseDocAttachment;
import com.margarita.vk_app.models.view.attachment.doc.DocAttachment;

public class DocAttachmentHolder extends BaseDocHolder<DocAttachment> {

    public DocAttachmentHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewHolder(DocAttachment docAttachment) {
        super.bindViewHolder(docAttachment);
        if (docAttachment.isNeedClick())
            setOnClickListener(docAttachment);
    }
}
