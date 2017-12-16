package com.margarita.vk_app.ui.fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.margarita.vk_app.R;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.presenter.MembersPresenter;
import com.margarita.vk_app.ui.fragment.base.BaseFeedFragment;

public class MembersFragment extends BaseFeedFragment {

    @InjectPresenter
    MembersPresenter presenter;

    @Override
    protected int onCreateToolbarTitle() {
        return R.string.drawer_item_members;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return presenter;
    }
}
