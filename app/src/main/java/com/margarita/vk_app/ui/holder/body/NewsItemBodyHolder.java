package com.margarita.vk_app.ui.holder.body;

import android.view.View;

import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.models.view.item.body.NewsItemBody;
import com.margarita.vk_app.ui.fragment.OpenedPostFragment;

public class NewsItemBodyHolder extends BaseBodyHolder<NewsItemBody> {

    public NewsItemBodyHolder(View itemView) {
        super(itemView);
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void bindViewHolder(NewsItemBody newsFeedItemBody) {
        tvText.setText(newsFeedItemBody.getText());
        String attachmentsString = newsFeedItemBody.getAttachmentsString();
        if (!attachmentsString.isEmpty())
            tvAttachments.setText(attachmentsString);
        else
            tvAttachments.setVisibility(View.GONE);

        addFragmentOnClick(fragmentManager,
                OpenedPostFragment.newInstance(newsFeedItemBody.getId()));

        setUpTextView(tvText, newsFeedItemBody.getText());
        setUpTextView(tvAttachments, newsFeedItemBody.getAttachmentsString());
    }

    @Override
    public void unbindViewHolder() {
        super.unbindViewHolder();
        clearOnClickListener();
    }
}
