package com.margarita.vk_app.models;

import android.support.annotation.LayoutRes;

import com.margarita.vk_app.R;

/**
 * Enum for all layout types in project
 */
public enum LayoutTypes {
    NewsFeedItemHeader(R.layout.item_news_header),
    NewsFeedItemBody(R.layout.item_news_body),
    NewsFeedItemFooter(R.layout.item_news_footer),
    Member(R.layout.member_item),
    Topic(R.layout.topic_item),
    InfoStatus(R.layout.item_info_status),
    InfoContacts(R.layout.item_info_contacts),
    InfoLinks(R.layout.item_info_links);

    private final int id;

    LayoutTypes(int resId) {
        this.id = resId;
    }

    @LayoutRes
    public int getId() {
        return id;
    }
}
