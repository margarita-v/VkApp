package com.margarita.vk_app.models.view.attachment.doc;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.attachment.doc.VkDocument;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.attachment.doc.DocAttachmentHolder;

import java.io.File;

public class DocAttachment extends BaseDocAttachment {

    private File file;

    private boolean needClick = true;

    public DocAttachment(VkDocument document) {
        super(document);
    }

    public DocAttachment(File file) {
        super(file.getName(), file.length());
        this.file = file;
        this.needClick = false;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new DocAttachmentHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentDoc;
    }

    public File getFile() {
        return file;
    }

    public boolean isNeedClick() {
        return needClick;
    }
}
