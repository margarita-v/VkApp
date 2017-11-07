package com.margarita.vk_app.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.NewsItemBody;

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBody> {

    private TextView tvText;

    private TextView tvAttachments;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);
        tvText = itemView.findViewById(R.id.tvText);
        tvAttachments = itemView.findViewById(R.id.tvAttachments);
    }

    @Override
    public void bindViewHolder(NewsItemBody newsFeedItemBody) {
        tvText.setText(newsFeedItemBody.getText());
        tvAttachments.setText(newsFeedItemBody.getAttachmentsString());
    }

    @Override
    public void unbindViewHolder() {
        tvText.setText(null);
        tvAttachments.setText(null);
    }

}
