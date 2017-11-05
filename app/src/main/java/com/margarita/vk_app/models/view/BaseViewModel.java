package com.margarita.vk_app.models.view;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.margarita.vk_app.R;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

public abstract class BaseViewModel {

    public BaseViewHolder createViewHolder(ViewGroup parent) {

        return onCreateViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(getType().getId(), parent, false));
    }

    protected abstract BaseViewHolder onCreateViewHolder(View view);

    public abstract LayoutTypes getType();

    public enum LayoutTypes {
        NewsFeedItemHeader(R.layout.item_news_header),
        NewsFeedItemBody(R.layout.item_news_body),
        NewsFeedItemFooter(R.layout.item_news_footer);

        private final int id;

        LayoutTypes(int resId) {
            this.id = resId;
        }

        @LayoutRes
        public int getId() {
            return id;
        }
    }
}
