package com.margarita.vk_app.mvp.view;

import com.margarita.vk_app.models.view.NewsItemFooter;

/**
 * Interface for showing opened post
 */
public interface OpenedPostView extends BaseFeedView {

    /**
     * Set up footer view model for opened post
     * @param footerViewModel Footer's view model
     */
    void setUpFooter(NewsItemFooter footerViewModel);
}
