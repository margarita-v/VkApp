package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.margarita.vk_app.common.manager.NetworkManager;
import com.margarita.vk_app.common.utils.DatabaseHelper;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.mvp.view.BaseFeedView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmObject;

/**
 * Base presenter for all presenters which are used in fragments
 * @param <V> Base class for View
 * @param <T> Base class for items which will be loaded and shown
 */
public abstract class BaseFeedPresenter<V extends BaseFeedView, T extends RealmObject>
        extends MvpPresenter<V> {

    /**
     * Pages sizes
     */
    private static final int START_PAGE_SIZE = 15;
    private static final int NEXT_PAGE_SIZE = 15;

    private boolean isLoading;

    @Inject
    NetworkManager networkManager;

    protected DatabaseHelper<T> databaseHelper;

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
