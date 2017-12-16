package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.CurrentUser;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.consts.ApiConstants;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.models.view.NewsItemBody;
import com.margarita.vk_app.models.view.NewsItemFooter;
import com.margarita.vk_app.models.view.profile.NewsItemHeader;
import com.margarita.vk_app.mvp.view.BaseFeedView;
import com.margarita.vk_app.rest.api.WallApi;
import com.margarita.vk_app.rest.model.request.WallGetRequest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView, WallItem> {

    /**
     * Filter by user's id
     */
    private boolean isIdFilterEnabled = false;

    @Inject
    WallApi wallApi;

    /**
     * Sort field for query to the database
     */
    private static final String SORT_FIELD = "date";

    public NewsFeedPresenter() {
        VkApplication.getApplicationComponent().inject(this);
    }

    public void setIdFilterEnabled(boolean enabled) {
        isIdFilterEnabled = enabled;
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return wallApi.get(new WallGetRequest(ApiConstants.GROUP_ID, count, offset)
                .toMap())
                // Convert Observable data from WallGetResponse to WallItem
                .flatMap(full -> Observable.fromIterable(
                        VkListHelper.getWallItemsInfo(full.getResponse())))
                .compose(applyFilter())
                .doOnNext(this::saveToDatabase)
                // Convert Observable data from WallItem to BaseViewModel
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
        return Observable.fromCallable(getListFromRealmCallable(SORT_FIELD, Sort.DESCENDING))
                .flatMap(Observable::fromIterable)
                .compose(applyFilter())
                .flatMap(wallItem -> Observable.fromIterable(parseToListItem(wallItem)));
    }

    @Override
    protected RealmQuery<WallItem> performQuery(Realm realm) {
        return realm.where(WallItem.class);
    }

    @Override
    protected List<WallItem> getQueryResult(Realm realm, RealmResults<WallItem> results) {
        return realm.copyFromRealm(results);
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
    private ObservableTransformer<WallItem, WallItem> applyFilter() {
        return baseItemObservable -> {
            String userId = CurrentUser.getId();
            return isIdFilterEnabled && userId != null
                    ? baseItemObservable.filter(
                            wallItem -> userId.equals(wallItem.getFromId().toString()))
                    : baseItemObservable;
        };
    }
}
