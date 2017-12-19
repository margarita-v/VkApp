package com.margarita.vk_app.ui.holder.attachment;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.attachment.PageAttachment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PageAttachmentHolder extends BaseAttachmentHolder<PageAttachment> {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public PageAttachmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(PageAttachment pageAttachment) {
        setOnClickListener(pageAttachment);
        tvTitle.setText(pageAttachment.getTitle());
    }

    @Override
    public void unbindViewHolder() {
        super.unbindViewHolder();
        clearTextView(tvTitle);
    }
}
