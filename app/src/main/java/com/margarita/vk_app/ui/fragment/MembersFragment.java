package com.margarita.vk_app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.margarita.vk_app.R;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.presenter.MembersPresenter;

import butterknife.ButterKnife;

public class MembersFragment extends BaseFeedFragment {

    @InjectPresenter
    MembersPresenter presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected int onCreateToolbarTitle() {
        return R.string.drawer_item_members;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return presenter;
    }
}
