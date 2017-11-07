package com.margarita.vk_app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.margarita.vk_app.R;
import com.margarita.vk_app.common.BaseAdapter;

public abstract class BaseFeedFragment extends BaseFragment {

    RecyclerView rvList;

    BaseAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpList(view);
        setUpAdapter();
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
}
