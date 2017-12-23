package com.margarita.vk_app.ui.fragment.open;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.presenter.open.OpenedCommentPresenter;

public class OpenedCommentFragment extends BaseOpenedFragment {

    @InjectPresenter
    OpenedCommentPresenter presenter;

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
    protected BaseFeedPresenter onCreateFeedPresenter() {
        presenter.setId(id);
        return presenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_opened_comment;
    }
}
