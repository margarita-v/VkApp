package com.margarita.vk_app.ui.fragment;

import android.os.Bundle;

import com.margarita.vk_app.R;

public class MyPostsFragment extends NewsFeedFragment {

    public MyPostsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setIdFilterEnabled(true);
    }

    @Override
    protected int onCreateToolbarTitle() {
        return R.string.drawer_item_my_posts;
    }
}
