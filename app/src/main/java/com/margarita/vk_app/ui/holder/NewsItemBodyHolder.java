package com.margarita.vk_app.ui.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.models.view.NewsItemBody;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBody> {

    @BindView(R.id.tvText)
    TextView tvText;

    @BindView(R.id.tvAttachments)
    TextView tvAttachments;

    @Inject
    Typeface googleFont;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        VkApplication.getApplicationComponent().inject(this);
        tvAttachments.setTypeface(googleFont);
    }

    @Override
    public void bindViewHolder(NewsItemBody newsFeedItemBody) {
        tvText.setText(newsFeedItemBody.getText());
        String attachmentsString = newsFeedItemBody.getAttachmentsString();
        if (!attachmentsString.isEmpty())
            tvAttachments.setText(attachmentsString);
        else
            tvAttachments.setVisibility(View.GONE);
    }

    @Override
    public void unbindViewHolder() {
        clearTextView(tvText);
        clearTextView(tvAttachments);
    }
}
