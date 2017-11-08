package com.margarita.vk_app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.BaseViewModel;
import com.margarita.vk_app.models.view.NewsItemBody;
import com.margarita.vk_app.models.view.NewsItemFooter;
import com.margarita.vk_app.models.view.NewsItemHeader;
import com.margarita.vk_app.rest.api.WallApi;
import com.margarita.vk_app.rest.model.request.WallGetRequestModel;
import com.margarita.vk_app.rest.model.response.WallGetResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NewsFeedFragment extends BaseFeedFragment {

    @Inject
    WallApi wallApi;

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
                // Преобразование данных Observable с WallGetResponse в WallItem
                .flatMap(new Function<WallGetResponse, ObservableSource<WallItem>>() {
                    @Override
                    public ObservableSource<WallItem> apply(WallGetResponse wallGetResponse)
                            throws Exception {
                        return io.reactivex.Observable.fromIterable(
                                VkListHelper.getWallItemsInfo(wallGetResponse.getResponse()));
                    }
                })
                // Преобразование данных Observable с WallItem в BaseViewModel
                .flatMap(new Function<WallItem, ObservableSource<BaseViewModel>>() {
                    @Override
                    public ObservableSource<BaseViewModel> apply(WallItem wallItem)
                            throws Exception {
                        List<BaseViewModel> result = new ArrayList<>();
                        result.add(new NewsItemHeader(wallItem));
                        result.add(new NewsItemBody(wallItem));
                        result.add(new NewsItemFooter(wallItem));
                        return io.reactivex.Observable.fromIterable(result);
                    }
                })
                /*
                 Преобразование данных в Observable из одного элемента - списка
                 Rx цепочка передает Observable<BaseViewModel>;
                 После преобразования будет Observable<List<BaseViewModel>>
                 Нужно для порционной передачи данных в адаптер:
                 свой список для каждой загрузки. */
                .toList()
                // Поток, в котором будет выполняться процесс Observable
                .subscribeOn(Schedulers.io())
                // Поток, в котором будут выполнять последующие операции.
                // Выполнится только добавление данных в адаптер и его оповещение
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaseViewModel>>() {
                    @Override
                    public void accept(List<BaseViewModel> items) throws Exception {
                        adapter.addItems(items);
                    }
                });
    }

    @Override
    protected int onCreateToolbarTitle() {
        return R.string.title_news;
    }
}
