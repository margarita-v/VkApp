package com.margarita.vk_app.models.view;

import android.view.View;

import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.counter.CommentsCounter;
import com.margarita.vk_app.models.view.counter.LikeCounter;
import com.margarita.vk_app.models.view.counter.RepostCounter;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.NewsItemFooterHolder;

public class NewsItemFooter extends BaseViewModel {

    private int id;
    private int ownerId;
    private long dateLong;

    private LikeCounter likeCounter;
    private CommentsCounter commentsCounter;
    private RepostCounter repostCounter;

    public NewsItemFooter(WallItem wallItem) {
        this.id = wallItem.getId();
        this.ownerId = wallItem.getOwnerId();
        this.dateLong = wallItem.getDate();

        this.likeCounter = new LikeCounter(wallItem.getLikes());
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

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public long getDateLong() {
        return dateLong;
    }

    public LikeCounter getLikeCounter() {
        return likeCounter;
    }

    public CommentsCounter getCommentsCounter() {
        return commentsCounter;
    }

    public RepostCounter getRepostCounter() {
        return repostCounter;
    }
}
