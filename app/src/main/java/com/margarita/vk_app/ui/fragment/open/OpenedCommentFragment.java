package com.margarita.vk_app.ui.fragment.open;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;

import butterknife.ButterKnife;

public class OpenedCommentFragment extends BaseOpenedFragment {

    public static OpenedCommentFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(ID_KEY, id);
        OpenedCommentFragment fragment = new OpenedCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_opened_wallitem;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return null;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_opened_comment;
    }
}
