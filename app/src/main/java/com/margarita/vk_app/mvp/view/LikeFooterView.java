package com.margarita.vk_app.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.margarita.vk_app.models.view.counter.LikeCounter;
import com.margarita.vk_app.models.view.item.footer.BaseFooterItem;

/**
 * Interface for performing like actions
 */
public interface LikeFooterView extends MvpView {

    /**
     * Function for addition or deleting a like
     * @param footerItem Footer of post which will be liked or disliked
     * @param likeCounter LikeCounter object for likes
     */
    void like(BaseFooterItem footerItem, LikeCounter likeCounter);
}
