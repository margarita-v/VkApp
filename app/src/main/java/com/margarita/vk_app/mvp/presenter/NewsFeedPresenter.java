package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.CurrentUser;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.consts.ApiConstants;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.BaseViewModel;
import com.margarita.vk_app.models.view.NewsItemBody;
import com.margarita.vk_app.models.view.NewsItemFooter;
import com.margarita.vk_app.models.view.NewsItemHeader;
import com.margarita.vk_app.mvp.view.BaseFeedView;
import com.margarita.vk_app.rest.api.WallApi;
import com.margarita.vk_app.rest.model.request.WallGetRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView> {

    private boolean isIdFilterEnabled = false;

    @Inject
    WallApi wallApi;

    // Sort field for query to the database
    private static final String SORT_FIELD = "date";

    public NewsFeedPresenter() {
        VkApplication.getApplicationComponent().inject(this);
    }

    public void setIdFilterEnabled(boolean enabled) {
        isIdFilterEnabled = enabled;
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return wallApi.get(new WallGetRequest(ApiConstants.OWNER_ID, count, offset)
                .toMap())
                // Преобразование данных Observable с WallGetResponse в WallItem
                .flatMap(full -> Observable.fromIterable(
                        VkListHelper.getWallItemsInfo(full.getResponse())))
                .compose(applyFilter())
                .doOnNext(this::saveToDatabase)
                // Преобразование данных Observable с WallItem в BaseViewModel
                .flatMap(wallItem -> {
                    List<BaseViewModel> items = new ArrayList<>();
                    items.add(new NewsItemHeader(wallItem));
                    items.add(new NewsItemBody(wallItem));
                    items.add(new NewsItemFooter(wallItem));
                    return Observable.fromIterable(items);
                });
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .compose(applyFilter())
                .flatMap(wallItem -> Observable.fromIterable(parseToListItem(wallItem)));
    }

    private Callable<List<WallItem>> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            RealmResults<WallItem> realmResults = realm.where(WallItem.class)
                    .findAllSorted(SORT_FIELD, Sort.DESCENDING);
            return realm.copyToRealm(realmResults);
        };
    }

    /**
     * Get full list item from wall item
     * @param wallItem Wall item which will be parsed
     * @return List item which consists of header, body and footer
     */
    private List<BaseViewModel> parseToListItem(WallItem wallItem) {
        List<BaseViewModel> result = new ArrayList<>();
        result.add(new NewsItemHeader(wallItem));
        result.add(new NewsItemBody(wallItem));
        result.add(new NewsItemFooter(wallItem));
        return result;
    }

    /**
     * Filter Observable by current user's id
     * @return Filtered Observable if filter is enabled and user's id is not null
     */
    protected ObservableTransformer<WallItem, WallItem> applyFilter() {
        return baseItemObservable -> {
            String userId = CurrentUser.getId();
            return isIdFilterEnabled && userId != null
                    ? baseItemObservable.filter(
                            wallItem -> userId.equals(wallItem.getFromId().toString()))
                    : baseItemObservable;
        };
    }
}
