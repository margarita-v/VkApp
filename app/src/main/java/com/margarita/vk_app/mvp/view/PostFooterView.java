package com.margarita.vk_app.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.counter.LikeCounter;

/**
 * Interface for showing post's footer
 */
public interface PostFooterView extends MvpView {

    /**
     * Show post's likes
     * @param likes LikeCounter for likes
     */
    void showLikes(LikeCounter likes);

    /**
     * Show post's comments
     * @param wallItem WallItem for getting comments
     */
    void showComments(WallItem wallItem);
}
