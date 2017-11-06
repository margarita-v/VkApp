package com.margarita.vk_app.models.view;

import android.view.View;

import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.NewsItemHeaderHolder;

public class NewsItemHeader extends BaseViewModel {

    private int id;

    private String profileName;
    private String profilePhoto;

    private boolean isRepost;
    private String repostProfileName;

    public NewsItemHeader(WallItem wallItem) {
        this.id = wallItem.getId();
        this.profileName = wallItem.getSenderName();
        this.profilePhoto = wallItem.getSenderPhoto();
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

    public int getId() {
        return id;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public boolean isRepost() {
        return isRepost;
    }

    public String getRepostProfileName() {
        return repostProfileName;
    }

}
