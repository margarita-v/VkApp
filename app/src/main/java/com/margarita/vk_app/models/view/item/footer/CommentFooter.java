package com.margarita.vk_app.models.view.item.footer;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.common.CommentItem;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.item.footer.CommentFooterHolder;

public class CommentFooter extends BaseFooterItem {

    public CommentFooter(CommentItem commentItem) {
        super(commentItem.getId(), commentItem.getDate(), commentItem.getLikes());
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new CommentFooterHolder(view, forOpenedComment);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.CommentFooter;
    }
}
