package com.margarita.vk_app.ui.holder.attachment;

import android.view.View;

import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.view.attachment.BaseAttachment;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

/**
 * Base view holder for all attachments
 */
public abstract class BaseAttachmentHolder<T extends BaseAttachment> extends BaseViewHolder<T> {

    public BaseAttachmentHolder(View view) {
        super(view);
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
    }

    /**
     * Method for setting on click listener to itemView
     * @param attachment Doc attachment
     */
    protected void setOnClickListener(T attachment) {
        itemView.setOnClickListener(view ->
                Utils.openUrlInActionView(attachment.getUrl(), view.getContext()));
    }
}
