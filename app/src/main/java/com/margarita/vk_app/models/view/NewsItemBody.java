package com.margarita.vk_app.models.view;

import android.view.View;

import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.NewsItemBodyHolder;

public class NewsItemBody extends BaseViewModel {

    private String text;
    private String attachmentsString;

    public NewsItemBody(WallItem wallItem) {
        super(wallItem.getId());

        if (wallItem.hasSharedRepost()) {
            this.text = wallItem.getSharedRepost().getText();
            this.attachmentsString = wallItem.getSharedRepost().getAttachmentsString();
        } else {
            this.text = wallItem.getText();
            this.attachmentsString = wallItem.getAttachmentsString();
        }
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemBodyHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemBody;
    }

    public String getText() {
        return text;
    }

    public String getAttachmentsString() {
        return attachmentsString;
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }
}
