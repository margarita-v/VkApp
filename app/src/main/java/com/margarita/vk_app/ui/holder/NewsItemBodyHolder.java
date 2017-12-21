package com.margarita.vk_app.ui.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.manager.VkFragmentManager;
import com.margarita.vk_app.models.view.NewsItemBody;
import com.margarita.vk_app.ui.fragment.OpenedPostFragment;

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

    @Inject
    VkFragmentManager fragmentManager;

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

        addFragmentOnClick(fragmentManager,
                OpenedPostFragment.newInstance(newsFeedItemBody.getId()));

        setUpTextView(tvText, newsFeedItemBody.getText());
        setUpTextView(tvAttachments, newsFeedItemBody.getAttachmentsString());
    }

    @Override
    public void unbindViewHolder() {
        clearOnClickListener();
        clearTextView(tvText);
        clearTextView(tvAttachments);
    }
}
