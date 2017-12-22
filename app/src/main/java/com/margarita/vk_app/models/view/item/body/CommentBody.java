package com.margarita.vk_app.models.view.item.body;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.common.CommentItem;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.body.CommentBodyHolder;

public class CommentBody extends BaseBodyItem {

    public CommentBody(CommentItem commentItem) {
        super(commentItem);
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new CommentBodyHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.CommentBody;
    }
}
