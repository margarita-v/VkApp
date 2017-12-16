package com.margarita.vk_app.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.margarita.vk_app.models.view.base.BaseViewModel;

import java.util.List;

/**
 * Interface for all lists representations
 */
public interface BaseFeedView extends MvpView {

    /**
     * Show loading animation (on swipe refresh)
     */
    void showRefreshing();

    /**
     * Hide swipe refreshing animation
     */
    void hideRefreshing();

    /**
     * Show loading animation on the center of screen
     */
    void showListProgress();

    /**
     * Hide loading animation
     */
    void hideListProgress();

    /**
     * Show error message
     * @param message Error message which will be shown
     */
    void showError(String message);

    /**
     * Set new list items
     * @param items New items which will be set
     */
    void setItems(List<BaseViewModel> items);

    /**
     * Add new items to list
     * @param items New items which will be added to the list
     */
    void addItems(List<BaseViewModel> items);
}
