package com.margarita.vk_app.models.view.profile;

import android.view.View;

import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.profile.NewsItemHeaderHolder;

public class NewsItemHeader extends ProfileViewModel {

    private boolean isRepost;
    private String repostProfileName;

    public NewsItemHeader(WallItem wallItem) {
        super(wallItem.getId(), wallItem.getSenderName(), wallItem.getSenderPhoto());
        this.isRepost = wallItem.hasSharedRepost();

        if (this.isRepost)
            this.repostProfileName = wallItem.getSharedRepost().getSenderName();
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemHeaderHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemHeader;
    }

    public boolean isRepost() {
        return isRepost;
    }

    public String getRepostProfileName() {
        return repostProfileName;
    }
}
