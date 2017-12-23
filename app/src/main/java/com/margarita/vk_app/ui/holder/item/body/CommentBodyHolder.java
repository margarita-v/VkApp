package com.margarita.vk_app.ui.holder.item.body;

import android.view.View;

import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.UIHelper;
import com.margarita.vk_app.models.view.item.body.CommentBody;
import com.margarita.vk_app.ui.fragment.open.OpenedCommentFragment;

public class CommentBodyHolder extends BaseBodyHolder<CommentBody> {

    public CommentBodyHolder(View itemView) {
        super(itemView);
        VkApplication.getApplicationComponent().inject(this);
        tvAttachments.setTypeface(googleFont);
    }

    @Override
    public void bindViewHolder(CommentBody commentBody) {
        UIHelper.setUpTextViewMessage(tvText, commentBody.getText());
        UIHelper.setUpTextViewVisible(tvAttachments, commentBody.getAttachmentsString());

        addFragmentOnClick(fragmentManager,
                OpenedCommentFragment.newInstance(commentBody.getId()));
    }
}
