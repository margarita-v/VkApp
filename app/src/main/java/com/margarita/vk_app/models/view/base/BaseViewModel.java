package com.margarita.vk_app.models.view.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

/**
 * Base class for all view models
 */
public abstract class BaseViewModel {

    protected boolean forOpenedComment;

    public BaseViewHolder createViewHolder(ViewGroup parent) {

        return onCreateViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(getType().getId(), parent, false));
    }

    protected abstract BaseViewHolder onCreateViewHolder(View view);

    public abstract LayoutTypes getType();

    /**
     * Function which checks if the current view model is just a decorator of list element.
     * By default view model is not a decorator.
     * For decorators method will be overridden.
     * In our case, header is a real list item, so body and footer are decorators for a header
     * (because adapter contains different view models,
     * but header, body and footer presents one list item).
     * @return True if the current view model is decorator
     */
    public boolean isItemDecorator() {
        return false;
    }

    public void setForOpenedComment(boolean forOpenedComment) {
        this.forOpenedComment = forOpenedComment;
    }
}
