package com.margarita.vk_app.mvp.presenter.complex.comments;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.DatabaseHelper;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.rest.api.BoardApi;
import com.margarita.vk_app.rest.model.request.group.BoardGetCommentsRequest;

import javax.inject.Inject;

import io.reactivex.Observable;

@InjectViewState
public class TopicCommentsPresenter extends BaseCommentsPresenter {

    @Inject
    BoardApi boardApi;

    public TopicCommentsPresenter() {
        super();
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return boardApi.getComments(
                new BoardGetCommentsRequest(
                        Utils.parseStringToInt(place.getOwnerId()),
                        Utils.parseStringToInt(place.getPostId()),
                        offset)
                        .toMap())
                .flatMap(full -> Observable.fromIterable(
                        VkListHelper.getComments(full.getResponse(), true)))
                .doOnNext(commentItem -> commentItem.setPlace(place))
                .doOnNext(DatabaseHelper::saveToDatabase)
                .flatMap(commentItem -> Observable.fromIterable(parseItemToList(commentItem)));
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return loadFromDatabase(true);
    }
}
