package com.margarita.vk_app.ui.fragment.comments;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.models.common.Place;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.presenter.complex.comments.CommentsPresenter;

public class CommentsFragment extends BaseCommentsFragment {

    @InjectPresenter
    CommentsPresenter presenter;

    public static CommentsFragment newInstance(Place place) {
        Bundle args = new Bundle();
        args.putParcelable(PLACE_KEY, place);
        CommentsFragment fragment = new CommentsFragment();
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
