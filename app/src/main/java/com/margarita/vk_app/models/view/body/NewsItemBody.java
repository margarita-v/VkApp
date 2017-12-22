package com.margarita.vk_app.models.view.body;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.body.NewsItemBodyHolder;

public class NewsItemBody extends BaseBodyItem {

    public NewsItemBody(WallItem wallItem) {
        super(wallItem);
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemBodyHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemBody;
    }
}
