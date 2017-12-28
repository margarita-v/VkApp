package com.margarita.vk_app.ui.holder.item.footer;

import android.view.View;

import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.counter.LikeCounter;
import com.margarita.vk_app.models.view.item.footer.CommentFooter;
import com.margarita.vk_app.mvp.view.PostFooterView;

public class CommentFooterHolder extends BaseFooterHolder<CommentFooter>
        implements PostFooterView {

    public CommentFooterHolder(View itemView) {
        super(itemView);
        VkApplication.getApplicationComponent().inject(this);
        tvLikesIcon.setTypeface(googleFont);
    }

    @Override
    public void showLikes(LikeCounter likes) {

    }

    @Override
    public void showComments(WallItem wallItem) {

    }

    @Override
    public void unbindViewHolder() {
        super.unbindViewHolder();
    }
}
