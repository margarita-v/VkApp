package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.models.view.BaseViewModel;
import com.margarita.vk_app.models.view.NewsItemBody;
import com.margarita.vk_app.models.view.NewsItemFooter;
import com.margarita.vk_app.models.view.NewsItemHeader;
import com.margarita.vk_app.mvp.view.BaseFeedView;
import com.margarita.vk_app.rest.api.WallApi;
import com.margarita.vk_app.rest.model.request.WallGetRequestModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

@InjectViewState
public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    WallApi wallApi;

    public NewsFeedPresenter() {
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateDataObservable(int offset, int count) {
        return wallApi.get(new WallGetRequestModel(-86529522).toMap())

                // Преобразование данных Observable с WallGetResponse в WallItem
                .flatMap(full -> Observable.fromIterable(
                        VkListHelper.getWallItemsInfo(full.getResponse())))

                // Преобразование данных Observable с WallItem в BaseViewModel
                .flatMap(wallItem -> {
                    List<BaseViewModel> items = new ArrayList<>();
                    items.add(new NewsItemHeader(wallItem));
                    items.add(new NewsItemBody(wallItem));
                    items.add(new NewsItemFooter(wallItem));
                    return Observable.fromIterable(items);
                });
    }
}
