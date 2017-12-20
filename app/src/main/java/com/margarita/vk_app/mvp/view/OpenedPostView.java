package com.margarita.vk_app.mvp.view;

import com.margarita.vk_app.models.view.NewsItemFooter;

/**
 * Interface for showing opened post
 */
public interface OpenedPostView extends BaseFeedView {

    /**
     * Set up footer for opened post
     * @param footer Post's footer
     */
    void setUpFooter(NewsItemFooter footer);
}
