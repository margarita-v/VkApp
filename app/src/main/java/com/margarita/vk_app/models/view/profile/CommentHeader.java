package com.margarita.vk_app.models.view.profile;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.common.CommentItem;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.profile.CommentHeaderHolder;

public class CommentHeader extends ProfileViewModel {

    public CommentHeader(CommentItem commentItem) {
        super(commentItem.getId(), commentItem.getSenderName(), commentItem.getPhoto());
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new CommentHeaderHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.CommentHeader;
    }
}
