package com.margarita.vk_app.ui.fragment.comments;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.models.common.Place;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.presenter.complex.comments.TopicCommentsPresenter;

public class TopicCommentsFragment extends BaseCommentsFragment {

    @InjectPresenter
    TopicCommentsPresenter presenter;

    public static TopicCommentsFragment newInstance(Place place) {
        Bundle args = new Bundle();
        args.putParcelable(PLACE_KEY, place);
        TopicCommentsFragment fragment = new TopicCommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        presenter.setPlace(place);
        return presenter;
    }
}
