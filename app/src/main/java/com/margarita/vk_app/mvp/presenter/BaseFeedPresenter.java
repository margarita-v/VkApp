package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.margarita.vk_app.models.view.BaseViewModel;
import com.margarita.vk_app.mvp.view.BaseFeedView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

public abstract class BaseFeedPresenter<V extends BaseFeedView> extends MvpPresenter<V> {

    private static final int START_PAGE_SIZE = 15;

    private static final int NEXT_PAGE_SIZE = 15;

    private boolean isLoading;

    private void loadData(ProgressType progressType, int offset, int count) {
        if (!isLoading) {
            isLoading = true;
            onLoadDataObservable(count, offset)
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

    /**
     * Load data from server
     */
    public abstract Observable<BaseViewModel> onLoadDataObservable(int offset, int count);

    /**
     * Load data from database
     */
    public abstract Observable<BaseViewModel> onRestoreDataObservable();

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
     * Save item to the database
     * @param item Item which will be added or updated
     */
    void saveToDatabase(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
    }

    /**
     * Progress types for different loading states
     */
    public enum ProgressType {
        Refreshing,     // On refresh data loading
        ListProgress,   // On first data loading
        Paging          // Load data on scrolling
    }
}
