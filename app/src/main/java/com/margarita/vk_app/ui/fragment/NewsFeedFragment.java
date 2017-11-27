package com.margarita.vk_app.ui.fragment;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.presenter.NewsFeedPresenter;
import com.margarita.vk_app.rest.api.WallApi;

import javax.inject.Inject;

public class NewsFeedFragment extends BaseFeedFragment {

    @Inject
    WallApi wallApi;

    @InjectPresenter
    NewsFeedPresenter presenter;

    public NewsFeedFragment() {
        // Required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    protected int onCreateToolbarTitle() {
        return R.string.title_news;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return presenter;
    }
}
