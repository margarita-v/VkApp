package com.margarita.vk_app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.margarita.vk_app.R;
import com.margarita.vk_app.common.BaseAdapter;
import com.margarita.vk_app.common.manager.VkLinearLayoutManager;
import com.margarita.vk_app.models.view.BaseViewModel;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.view.BaseFeedView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseFeedFragment extends BaseFragment implements BaseFeedView {

    @BindView(R.id.rvList)
    RecyclerView rvList;

    BaseAdapter adapter;

    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeRefreshLayout;

    protected ProgressBar progressBar;
    protected BaseFeedPresenter presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setUpList();
        setUpAdapter();
        setUpSwipeContainer();
        presenter = onCreateFeedPresenter();
        presenter.loadStart();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_news_feed;
    }

    @Override
    protected int onCreateToolbarTitle() {
        return 0;
    }

    private void setUpList() {
        VkLinearLayoutManager layoutManager = new VkLinearLayoutManager(getContext());
        rvList.setLayoutManager(layoutManager);
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (layoutManager.isOnNextPagePosition())
                    presenter.loadNext(adapter.getRealItemCount());
            }
        });

        ((SimpleItemAnimator) rvList.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    private void setUpAdapter() {
        adapter = new BaseAdapter();
        rvList.setAdapter(adapter);
    }

    private void setUpSwipeContainer() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(
                () -> onCreateFeedPresenter().loadRefresh());
        progressBar = getBaseActivity().getProgressBar();
    }

    protected abstract BaseFeedPresenter onCreateFeedPresenter();

    @Override
    public void showRefreshing() {

    }

    @Override
    public void hideRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showListProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getBaseActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setItems(List<BaseViewModel> items) {
        adapter.setItems(items);
    }

    @Override
    public void addItems(List<BaseViewModel> items) {
        adapter.addItems(items);
    }
}
