package com.margarita.vk_app.ui.fragment;

import com.margarita.vk_app.R;

public class NewsFeedFragment extends BaseFragment {

    public NewsFeedFragment() {
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_news_feed;
    }

    @Override
    protected int onCreateToolbarTitle() {
        return R.string.title_news;
    }
}
