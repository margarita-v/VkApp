package com.margarita.vk_app.ui.holder.attachment.link;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.attachment.link.BaseLinkAttachment;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Base view holder for link attachments
 */
abstract class BaseLinkHolder<T extends BaseLinkAttachment> extends BaseViewHolder<T> {

    @BindView(R.id.tvTitle)
    public TextView title;

    @BindView(R.id.tvAttachmentUrl)
    public TextView url;

    BaseLinkHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
