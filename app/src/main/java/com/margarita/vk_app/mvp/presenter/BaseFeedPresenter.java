package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.margarita.vk_app.common.manager.NetworkManager;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.mvp.view.BaseFeedView;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Base presenter for all presenters which are used in fragments
 * @param <V> Base class for View
 * @param <T> Base class for items which will be loaded and shown
 */
public abstract class BaseFeedPresenter<V extends BaseFeedView, T> extends MvpPresenter<V> {

    /**
     * Pages sizes
     */
    private static final int START_PAGE_SIZE = 15;
    private static final int NEXT_PAGE_SIZE = 15;

    /**
     * Fields for query to the database
     */
    private static final String SORT_FIELD = "id";
    private static final String FIELD_NAME = "id";

    private boolean isLoading;

    @Inject
    NetworkManager networkManager;

    private void loadData(ProgressType progressType, int offset, int count) {
        if (!isLoading) {
            isLoading = true;

            networkManager.getNetworkStateObservable()
                    .flatMap(isVkAvailable -> {
                        if (!isVkAvailable && offset > 0)
                            return Observable.empty();
                        return isVkAvailable
                                ? onLoadDataObservable(offset, count)
                                : onRestoreDataObservable();
                    })
                    .toList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> onLoadingStart(progressType))
                    .doFinally(() -> onLoadingFinish(progressType))
                    .subscribe(
                            items -> onLoadingSuccess(progressType, items),
                            error -> {
                                error.printStackTrace();
                                onLoadingFailed(error);
                            });
        }
    }

    //region Methods for getting data from server or from the local database

    /**
     * Load data from server
     */
    public abstract Observable<BaseViewModel> onLoadDataObservable(int offset, int count);

    /**
     * Load data from database
     */
    public abstract Observable<BaseViewModel> onRestoreDataObservable();

    //endregion

    //region Methods for getting items from the local database as Callable

    /**
     * Get list of items from local database as Callable
     * @param sort Sort order for result items
     * @return List of items as Callable
     */
    protected Callable<List<T>> getListFromRealmCallable(Sort sort) {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            // Perform the query which depends on item's type
            RealmResults<T> results = getListItems(realm)
                    .findAllSorted(getSortField(), sort);
            return getQueryResult(realm, results);
        };
    }

    /**
     * Get single item from local database as Callable
     * @param value Value for "where" condition in query
     * @return Single item as Callable
     */
    protected Callable<T> getItemFromRealmCallable(Integer value) {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            T result = getSingleItem(realm, value)
                    .findFirst();
            return getQueryResult(realm, result);
        };
    }
    //endregion

    //region Methods for the database queries

    /**
     * Perform common query to the database
     * @param realm Realm instance for access to the database
     * @return Set of query result
     */
    protected abstract RealmQuery<T> performQuery(Realm realm);

    /**
     * Get a list of items as a subquery
     * @param realm Realm instance for access to the database
     * @return List of realm query items
     */
    protected RealmQuery<T> getListItems(Realm realm) {
        return performQuery(realm);
    }

    /**
     * Get items as a subquery with "where" condition
     * @param realm Realm instance for access to the database
     * @param value Value for "where" condition in query
     * @return Query result for "where" condition
     */
    RealmQuery<T> getSingleItem(Realm realm, Integer value) {
        return performQuery(realm)
                .equalTo(getFieldName(), value);
    }

    /**
     * Get a list of items as a query result
     * @param realm Realm instance for access to the database
     * @param results Set of query result
     * @return List of items
     */
    protected abstract List<T> getQueryResult(Realm realm, RealmResults<T> results);

    /**
     * Get a single item as a query result
     * @param realm Realm instance for access to the database
     * @param result Query result
     * @return Single item
     */
    protected abstract T getQueryResult(Realm realm, T result);

    /**
     * Function which returns a name of sort field
     * @return Sort field's name
     */
    protected String getSortField() {
        return SORT_FIELD;
    }

    /**
     * Function which returns a name of field for "where" condition
     * @return Field name for "where" condition
     */
    protected String getFieldName() {
        return FIELD_NAME;
    }
    //endregion

    /**
     * Show different progress bars which type depends on progress type
     * @param progressType Current progress type
     */
    private void showProgress(ProgressType progressType) {
        switch (progressType) {
            case Refreshing:
                getViewState().showRefreshing();
                break;
            case ListProgress:
                getViewState().showListProgress();
                break;
        }
    }

    /**
     * Hide progress bar
     * @param progressType Current progress type
     */
    private void hideProgress(ProgressType progressType) {
        switch (progressType) {
            case Refreshing:
                getViewState().hideRefreshing();
                break;
            case ListProgress:
                getViewState().hideListProgress();
                break;
        }
    }

    //region All kinds of data loading
    /**
     * Initial data loading
     */
    public void loadStart() {
        loadData(ProgressType.ListProgress, 0, START_PAGE_SIZE);
    }

    /**
     * Load next items on scrolling
     */
    public void loadNext(int offset) {
        loadData(ProgressType.Paging, offset, NEXT_PAGE_SIZE);
    }

    /**
     * Refresh data loading
     */
    public void loadRefresh() {
        loadData(ProgressType.Refreshing, 0, START_PAGE_SIZE);
    }
    //endregion

    //region Common loading actions

    private void onLoadingStart(ProgressType progressType) {
        showProgress(progressType);
    }

    private void onLoadingFinish(ProgressType progressType) {
        isLoading = false;
        hideProgress(progressType);
    }

    private void onLoadingSuccess(ProgressType progressType, List<BaseViewModel> items) {
        if (progressType == ProgressType.Paging)
            getViewState().addItems(items);
        else
            getViewState().setItems(items);
    }

    private void onLoadingFailed(Throwable throwable) {
        getViewState().showError(throwable.getMessage());
    }
    //endregion

    /**
     * Progress types for different loading states
     */
    public enum ProgressType {
        Refreshing,     // On refresh data loading
        ListProgress,   // On first data loading
        Paging          // Load data on scrolling
    }

    /**
     * Interface for parsing item to list of view models
     * if the item is complex
     * @param <ItemType> Type of item which will be parsed
     */
    public interface ParsingInterface<ItemType> {

        /**
         * Parse item to list of view models
         * @param item Item which will be parsed
         * @return List of view models which is associated with item
         */
        List<BaseViewModel> parseItemToList(ItemType item);
    }
}
