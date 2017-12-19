package com.margarita.vk_app.ui.holder.attachment.doc;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.view.attachment.doc.BaseDocAttachment;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Base view holder for doc attachments
 */
abstract class BaseDocHolder<T extends BaseDocAttachment> extends BaseViewHolder<T> {


    @BindView(R.id.tvAttachmentTitle)
    private TextView tvTitle;

    @BindView(R.id.tvAttachmentExt)
    private TextView tvExt;

    @BindView(R.id.tvAttachmentSize)
    private TextView tvSize;

    BaseDocHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(T baseDocAttachment) {
        tvTitle.setText(baseDocAttachment.getTitle());
        tvSize.setText(baseDocAttachment.getSize());
        tvExt.setText(baseDocAttachment.getExt());
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        clearTextView(tvTitle);
        clearTextView(tvExt);
        clearTextView(tvSize);
    }

    /**
     * Method for setting on click listener to itemView
     * @param baseDocAttachment Doc attachment
     */
    void setOnClickListener(BaseDocAttachment baseDocAttachment) {
        itemView.setOnClickListener(view ->
                Utils.openUrlInActionView(baseDocAttachment.getUrl(), view.getContext()));
    }
}
