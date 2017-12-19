package com.margarita.vk_app.models.view.attachment.doc;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.attachment.doc.Size;
import com.margarita.vk_app.models.attachment.doc.VkDocument;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.attachment.doc.DocImageHolder;

import java.util.List;

public class DocImageAttachment extends BaseDocAttachment {

    private String image;

    public DocImageAttachment(VkDocument document) {
        super(document);
        // Get the last doc image
        List<Size> sizes = document.getPreview().getPhoto().getSizes();
        this.image = sizes.get(sizes.size() - 1).getSrc();
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new DocImageHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentDocImage;
    }

    public String getImage() {
        return image;
    }
}
