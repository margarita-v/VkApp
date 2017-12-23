package com.margarita.vk_app.ui.fragment;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.margarita.vk_app.R;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.presenter.complex.InfoPresenter;
import com.margarita.vk_app.ui.fragment.base.BaseFeedFragment;

public class InfoFragment extends BaseFeedFragment {

    @InjectPresenter
    InfoPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasEndlessList(false);
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return presenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.drawer_item_info;
    }
}
