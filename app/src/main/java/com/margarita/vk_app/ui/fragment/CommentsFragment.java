package com.margarita.vk_app.ui.fragment;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.models.common.Place;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.presenter.complex.comments.CommentsPresenter;
import com.margarita.vk_app.ui.fragment.base.BaseFeedFragment;

public class CommentsFragment extends BaseFeedFragment {

    @InjectPresenter
    CommentsPresenter presenter;

    private Place place;

    /**
     * Key for bundle
     */
    private static final String PLACE_KEY = "Place";

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
        Bundle args = getArguments();
        if (args != null)
            place = getArguments().getParcelable(PLACE_KEY);
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        presenter.setPlace(place);
        return presenter;
    }

    @Override
    protected int onCreateToolbarTitle() {
        return R.string.screen_name_comments;
    }
}
