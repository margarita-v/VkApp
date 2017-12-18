package com.margarita.vk_app.ui.holder.attachment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.view.attachment.DocImageAttachment;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DocImageAttachmentHolder extends BaseViewHolder<DocImageAttachment> {

    @BindView(R.id.tvAttachmentTitle)
    private TextView tvTitle;

    @BindView(R.id.tvAttachmentExt)
    private TextView tvExt;

    @BindView(R.id.tvAttachmentSize)
    private TextView tvSize;

    @BindView(R.id.ivAttachmentImage)
    private ImageView ivAttachmentImage;

    public DocImageAttachmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(DocImageAttachment docImageAttachment) {
        itemView.setOnClickListener(view ->
                Utils.openUrlInActionView(docImageAttachment.getUrl(), view.getContext()));

        tvTitle.setText(docImageAttachment.getTitle());
        tvSize.setText(docImageAttachment.getSize());
        tvExt.setText(docImageAttachment.getExt());

        loadImage(docImageAttachment.getImage(), ivAttachmentImage);
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        clearTextView(tvTitle);
        clearTextView(tvExt);
        clearTextView(tvSize);
        clearImageView(ivAttachmentImage);
    }
}
