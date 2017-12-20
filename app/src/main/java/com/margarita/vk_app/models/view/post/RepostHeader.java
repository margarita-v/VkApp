package com.margarita.vk_app.models.view.post;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

public class RepostHeader extends BaseOpenedPost {

    private long date;

    public RepostHeader(WallItem wallItem) {
        super(wallItem);
        this.date = wallItem.getDate();
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return null;
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.OpenedPostRepostHeader;
    }

    public long getDate() {
        return date;
    }
}
