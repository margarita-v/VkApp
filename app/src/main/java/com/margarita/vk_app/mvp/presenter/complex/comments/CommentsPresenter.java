package com.margarita.vk_app.mvp.presenter.complex.comments;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.DatabaseHelper;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.rest.api.WallApi;
import com.margarita.vk_app.rest.model.request.owner.WallGetCommentsRequest;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CommentsPresenter extends BaseCommentsPresenter {

    @Inject
    WallApi wallApi;

    public CommentsPresenter() {
        super();
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return wallApi.getComments(
                new WallGetCommentsRequest(
                        Utils.parseStringToInt(place.getOwnerId()),
                        offset,
                        Utils.parseStringToInt(place.getPostId()))
                        .toMap())
                .flatMap(full -> Observable.fromIterable(
                        VkListHelper.getComments(full.getResponse(), false)))
                .subscribeOn(Schedulers.io())
                .doOnNext(commentItem -> {
                    commentItem.setPlace(place);
                    DatabaseHelper.saveToDatabase(commentItem);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(commentItem -> Observable.fromIterable(parseItemToList(commentItem)));
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return loadFromDatabase(false);
    }
}
