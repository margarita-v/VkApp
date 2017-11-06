package com.margarita.vk_app.models.view;

import android.view.View;

import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.NewsItemBodyHolder;

public class NewsFeedItemBody extends BaseViewModel {

    private int id;

    private String text;

    public NewsFeedItemBody(WallItem wallItem) {
        this.id = wallItem.getId();
        this.text = wallItem.getText();
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemBodyHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemBody;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
