package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.consts.ApiConstants;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.NewsItemFooter;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.models.view.post.PostHeader;
import com.margarita.vk_app.models.view.post.RepostHeader;
import com.margarita.vk_app.mvp.view.OpenedPostView;
import com.margarita.vk_app.rest.api.WallApi;
import com.margarita.vk_app.rest.model.request.WallGetByIdRequest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

@InjectViewState
public class OpenedPostPresenter extends BaseFeedPresenter<OpenedPostView, WallItem> {

    private int id;

    @Inject
    WallApi wallApi;

    public OpenedPostPresenter() {
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return wallApi.getById(new WallGetByIdRequest(ApiConstants.GROUP_ID, id).toMap())
                .flatMap(full -> Observable.fromIterable(
                        VkListHelper.getWallItemsInfo(full.getResponse())))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(setUpFooterConsumer)
                .observeOn(Schedulers.io())
                .doOnNext(this::saveToDatabase)
                .flatMap(getPostInfo);
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return Observable.fromCallable(getItemFromRealmCallable(id))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(setUpFooterConsumer)
                .observeOn(Schedulers.io())
                .flatMap(getPostInfo);
    }

    @Override
    protected RealmQuery<WallItem> performQuery(Realm realm) {
        return realm.where(WallItem.class);
    }

    @Override
    protected List<WallItem> getQueryResult(Realm realm, RealmResults<WallItem> results) {
        return realm.copyFromRealm(results);
    }

    @Override
    protected WallItem getQueryResult(Realm realm, WallItem result) {
        return realm.copyFromRealm(result);
    }

    /**
     * Function for adding all view models for wall item in common list
     * @param list List of all view models
     * @param wallItem Wall item which view models will be added to the list
     */
    private void addAll(List<BaseViewModel> list, WallItem wallItem) {
        list.addAll(VkListHelper.getAttachmentVkItems(wallItem.getAttachments()));
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Consumer for doOnNext method in Rx sequence
     */
    private Consumer<WallItem> setUpFooterConsumer = wallItem -> {
        NewsItemFooter footer = new NewsItemFooter(wallItem);
        getViewState().setUpFooter(footer);
    };

    /**
     * Function for flatMap method in Rx sequence
     * for getting a list of view models as Observable
     */
    private Function<WallItem, ObservableSource<BaseViewModel>> getPostInfo = wallItem -> {
        List<BaseViewModel> list = new ArrayList<>();
        List<BaseViewModel> forwardedList = new ArrayList<>();

        list.add(new PostHeader(wallItem));
        addAll(list, wallItem);

        if (wallItem.hasSharedRepost()) {
            WallItem repost = wallItem.getSharedRepost();
            forwardedList.add(new RepostHeader(repost));
            addAll(forwardedList, repost);
        }
        return Observable.fromIterable(list)
                .concatWith(Observable.fromIterable(forwardedList));
    };
}
