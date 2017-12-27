package com.margarita.vk_app.models.view.post;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.common.CommentItem;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.post.PostHeaderHolder;

public class PostHeader extends BaseOpenedPost {

    public PostHeader(WallItem wallItem) {
        super(wallItem);
    }

    public PostHeader(CommentItem commentItem) {
        super(commentItem);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.OpenedPostHeader;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new PostHeaderHolder(view, forOpenedComment);
    }
}
