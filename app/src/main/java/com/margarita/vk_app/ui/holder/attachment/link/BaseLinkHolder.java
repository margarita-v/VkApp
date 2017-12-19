package com.margarita.vk_app.ui.holder.attachment.link;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.view.attachment.link.BaseLinkAttachment;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Base view holder for link attachments
 */
abstract class BaseLinkHolder<T extends BaseLinkAttachment> extends BaseViewHolder<T> {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvAttachmentUrl)
    TextView tvUrl;

    BaseLinkHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(T attachment) {
        itemView.setOnClickListener(view ->
                Utils.openUrlInActionView(attachment.getUrl(), view.getContext()));
        tvTitle.setText(attachment.getTitle());
        tvUrl.setText(attachment.getUrl());
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        clearTextView(tvTitle);
        clearTextView(tvUrl);
    }
}
