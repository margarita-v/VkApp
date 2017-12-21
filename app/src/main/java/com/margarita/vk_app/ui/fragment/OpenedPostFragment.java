package com.margarita.vk_app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.models.view.NewsItemFooter;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.presenter.OpenedPostPresenter;
import com.margarita.vk_app.mvp.view.OpenedPostView;
import com.margarita.vk_app.ui.fragment.base.BaseFeedFragment;
import com.margarita.vk_app.ui.holder.NewsItemFooterHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenedPostFragment extends BaseFeedFragment implements OpenedPostView {

    @BindView(R.id.rvFooter)
    View viewFooter;

    @InjectPresenter
    OpenedPostPresenter presenter;

    private int id;

    /**
     * Key for bundle
     */
    private static final String ID_KEY = "id";

    public static OpenedPostFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(ID_KEY, id);
        OpenedPostFragment fragment = new OpenedPostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VkApplication.getApplicationComponent().inject(this);

        Bundle args = getArguments();
        if (args != null) {
            this.id = args.getInt(ID_KEY);
        }
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
    public int onCreateToolbarTitle() {
        return R.string.screen_name_opened_post;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        presenter.setId(id);
        return presenter;
    }

    @Override
    public void setUpFooter(NewsItemFooter footerViewModel) {
        viewFooter.setVisibility(View.VISIBLE);
        new NewsItemFooterHolder(viewFooter).bindViewHolder(footerViewModel);
    }
}
