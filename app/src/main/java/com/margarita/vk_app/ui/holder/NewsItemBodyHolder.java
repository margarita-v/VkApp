package com.margarita.vk_app.ui.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.models.view.NewsItemBody;

import javax.inject.Inject;

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBody> {

    private TextView tvText;

    private TextView tvAttachments;

    @Inject
    Typeface googleFont;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);
        VkApplication.getApplicationComponent().inject(this);

        tvText = itemView.findViewById(R.id.tvText);
        tvAttachments = itemView.findViewById(R.id.tvAttachments);

        if (tvAttachments != null) {
            tvAttachments.setTypeface(googleFont);
        }
    }

    @Override
    public void bindViewHolder(NewsItemBody newsFeedItemBody) {
        tvText.setText(newsFeedItemBody.getText());
        tvAttachments.setText(newsFeedItemBody.getAttachmentsString());
    }

    @Override
    public void unbindViewHolder() {
        clearTextView(tvText);
        clearTextView(tvAttachments);
    }
}
