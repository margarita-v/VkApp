package com.margarita.vk_app.ui.holder.attachment.doc;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.attachment.doc.BaseDocAttachment;
import com.margarita.vk_app.ui.holder.attachment.BaseAttachmentHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Base view holder for doc attachments
 */
abstract class BaseDocHolder<T extends BaseDocAttachment> extends BaseAttachmentHolder<T> {

    @BindView(R.id.tvAttachmentTitle)
    TextView tvTitle;

    @BindView(R.id.tvAttachmentExt)
    TextView tvExt;

    @BindView(R.id.tvAttachmentSize)
    TextView tvSize;

    BaseDocHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(T attachment) {
        tvTitle.setText(attachment.getTitle());
        tvSize.setText(attachment.getSize());
        tvExt.setText(attachment.getExt());
    }

    @Override
    public void unbindViewHolder() {
        super.unbindViewHolder();
        clearTextView(tvTitle);
        clearTextView(tvExt);
        clearTextView(tvSize);
    }
}
