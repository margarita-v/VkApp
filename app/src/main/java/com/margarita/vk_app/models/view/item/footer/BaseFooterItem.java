package com.margarita.vk_app.models.view.item.footer;

import com.margarita.vk_app.models.countable.Likes;
import com.margarita.vk_app.models.view.base.BaseIdModel;
import com.margarita.vk_app.models.view.counter.LikeCounter;

/**
 * Base class for items which represent some item's footer
 */
public abstract class BaseFooterItem extends BaseIdModel {

    private long dateLong;

    private LikeCounter likeCounter;

    BaseFooterItem(int id, long date, Likes likes) {
        super(id);
        this.dateLong = date;
        this.likeCounter = new LikeCounter(likes);
    }

    public long getDateLong() {
        return dateLong;
    }

    public LikeCounter getLikeCounter() {
        return likeCounter;
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }
}
