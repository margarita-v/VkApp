package com.margarita.vk_app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.margarita.vk_app.R;
import com.margarita.vk_app.common.BaseAdapter;

public abstract class BaseFeedFragment extends BaseFragment {

    RecyclerView rvList;
    BaseAdapter adapter;

    protected SwipeRefreshLayout swipeRefreshLayout;
    protected ProgressBar progressBar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpList(view);
        setUpAdapter();
        setUpSwipeContainer(view);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_news_feed;
    }

    private void setUpList(View rootView) {
        rvList = rootView.findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setUpAdapter() {
        adapter = new BaseAdapter();
        rvList.setAdapter(adapter);
    }

    private void setUpSwipeContainer(View rootView) {
        swipeRefreshLayout = rootView.findViewById(R.id.swipeContainer);
        progressBar = getBaseActivity().getProgressBar();
    }
}
