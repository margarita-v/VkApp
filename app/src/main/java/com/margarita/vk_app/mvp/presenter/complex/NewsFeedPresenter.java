package com.margarita.vk_app.mvp.presenter.complex;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.CurrentUser;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.DatabaseHelper;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.consts.ApiConstants;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.models.view.item.body.NewsItemBody;
import com.margarita.vk_app.models.view.item.footer.NewsItemFooter;
import com.margarita.vk_app.models.view.profile.NewsItemHeader;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.view.BaseFeedView;
import com.margarita.vk_app.rest.api.WallApi;
import com.margarita.vk_app.rest.model.request.owner.WallGetRequest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.Sort;

@InjectViewState
public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView, WallItem>
        implements BaseFeedPresenter.ParsingInterface<WallItem> {

    /**
     * Filter by user's id
     */
    private boolean isIdFilterEnabled = false;

    @Inject
    WallApi wallApi;

    /**
     * Sort field for this class which overrides parent's sort field
     */
    private static final String SORT_FIELD = "date";

    public NewsFeedPresenter() {
        VkApplication.getApplicationComponent().inject(this);
        databaseHelper = new DatabaseHelper<WallItem>() {
            @Override
            public RealmQuery<WallItem> performQuery(Realm realm) {
                return realm.where(WallItem.class);
            }

            @Override
            protected String getSortField() {
                return SORT_FIELD;
            }
        };
    }

    public void setIdFilterEnabled(boolean enabled) {
        isIdFilterEnabled = enabled;
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return wallApi.get(new WallGetRequest(ApiConstants.GROUP_CONTENT_ID, count, offset)
                .toMap())
                // Convert Observable data from WallGetResponse to WallItem
                .flatMap(full -> Observable.fromIterable(
                        VkListHelper.getWallItemsInfo(full.getResponse())))
                .compose(applyFilter())
                .doOnNext(DatabaseHelper::saveToDatabase)
                // Convert Observable data from WallItem to BaseViewModel
                .flatMap(wallItem -> Observable.fromIterable(parseItemToList(wallItem)));
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return Observable.fromCallable(
                databaseHelper.getListFromRealmCallable(Sort.DESCENDING))
                .flatMap(Observable::fromIterable)
                .compose(applyFilter())
                .flatMap(wallItem -> Observable.fromIterable(parseItemToList(wallItem)));
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

    @Override
    public List<BaseViewModel> parseItemToList(WallItem item) {
        List<BaseViewModel> result = new ArrayList<>();
        result.add(new NewsItemHeader(item));
        result.add(new NewsItemBody(item));
        result.add(new NewsItemFooter(item));
        return result;
    }
}
