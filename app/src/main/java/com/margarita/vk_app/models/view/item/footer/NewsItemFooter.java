package com.margarita.vk_app.models.view.item.footer;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.counter.CommentsCounter;
import com.margarita.vk_app.models.view.counter.RepostCounter;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.NewsItemFooterHolder;

public class NewsItemFooter extends BaseFooterItem {

    private int ownerId;
    private CommentsCounter commentsCounter;
    private RepostCounter repostCounter;

    public NewsItemFooter(WallItem wallItem) {
        super(wallItem.getId(), wallItem.getDate(), wallItem.getLikes());
        this.ownerId = wallItem.getOwnerId();
        this.commentsCounter = new CommentsCounter(wallItem.getComments());
        this.repostCounter = new RepostCounter(wallItem.getReposts());
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemFooterHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemFooter;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public CommentsCounter getCommentsCounter() {
        return commentsCounter;
    }

    public RepostCounter getRepostCounter() {
        return repostCounter;
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }
}
