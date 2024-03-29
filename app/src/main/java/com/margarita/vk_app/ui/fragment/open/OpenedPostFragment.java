package com.margarita.vk_app.ui.fragment.open;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.models.view.item.footer.NewsItemFooter;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.presenter.open.OpenedPostPresenter;
import com.margarita.vk_app.mvp.view.OpenedPostView;
import com.margarita.vk_app.ui.holder.item.footer.NewsItemFooterHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenedPostFragment extends BaseOpenedFragment implements OpenedPostView {

    @BindView(R.id.rvFooter)
    View viewFooter;

    @InjectPresenter
    OpenedPostPresenter presenter;

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
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
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
        // For correct display of opened post's footer fonts
        new NewsItemFooterHolder(viewFooter).bindViewHolder(footerViewModel);
    }
}
