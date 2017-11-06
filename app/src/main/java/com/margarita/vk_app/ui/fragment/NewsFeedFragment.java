package com.margarita.vk_app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.BaseAdapter;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.NewsItemBody;
import com.margarita.vk_app.rest.api.WallApi;
import com.margarita.vk_app.rest.model.request.WallGetRequestModel;
import com.margarita.vk_app.rest.model.response.WallGetResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedFragment extends BaseFragment {

    @Inject
    WallApi wallApi;

    RecyclerView rvList;

    BaseAdapter adapter;

    public NewsFeedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        wallApi.get(new WallGetRequestModel(-86529522).toMap())
                .enqueue(new Callback<WallGetResponse>() {
            @Override
            public void onResponse(Call<WallGetResponse> call,
                                   Response<WallGetResponse> response) {
                if (response.body() != null) {

                    List<NewsItemBody> list = new ArrayList<>();
                    List<WallItem> wallItems = response.body().getResponse().getItems();

                    for (WallItem item: wallItems) {
                        list.add(new NewsItemBody(item));
                    }

                    adapter.setItems(list);

                    Toast.makeText(getActivity(),
                            "Likes: " + response.body()
                                    .getResponse().getItems().get(0).getLikes().getCount(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<WallGetResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

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

    @Override
    protected int onCreateToolbarTitle() {
        return R.string.title_news;
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
