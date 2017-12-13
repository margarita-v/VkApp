package com.margarita.vk_app.models.view;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.margarita.vk_app.R;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

public abstract class BaseViewModel {

    protected int id;

    public BaseViewModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public BaseViewHolder createViewHolder(ViewGroup parent) {

        return onCreateViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(getType().getId(), parent, false));
    }

    protected abstract BaseViewHolder onCreateViewHolder(View view);

    public abstract LayoutTypes getType();

    public enum LayoutTypes {
        NewsFeedItemHeader(R.layout.item_news_header),
        NewsFeedItemBody(R.layout.item_news_body),
        NewsFeedItemFooter(R.layout.item_news_footer),
        Member(R.layout.member_item),
        Topic(R.layout.topic_item);

        private final int id;

        LayoutTypes(int resId) {
            this.id = resId;
        }

        @LayoutRes
        public int getId() {
            return id;
        }
    }

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
}
