package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.consts.ApiConstants;
import com.margarita.vk_app.models.common.Topic;
import com.margarita.vk_app.models.view.BaseViewModel;
import com.margarita.vk_app.models.view.TopicViewModel;
import com.margarita.vk_app.mvp.view.BaseFeedView;
import com.margarita.vk_app.rest.api.BoardApi;
import com.margarita.vk_app.rest.model.request.BoardGetTopicsRequest;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class BoardPresenter extends BaseFeedPresenter<BaseFeedView, Topic> {

    @Inject
    BoardApi boardApi;

    /**
     * Fields for query to the database
     */
    private static final String FIELD_NAME = "groupId";
    private static final String SORT_FIELD = "id";

    public BoardPresenter() {
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return boardApi.getTopics(
                new BoardGetTopicsRequest(ApiConstants.GROUP_ID, count, offset)
                        .toMap())
                // Convert Observable data from TopicGetResponse to Topic
                .flatMap(full -> Observable.fromIterable(full.getResponse().getItems()))
                .doOnNext(topic -> topic.setGroupId(ApiConstants.GROUP_ID))
                .doOnNext(this::saveToDatabase)
                // Create TopicViewModel for every Topic object
                .map(TopicViewModel::new);
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable(SORT_FIELD, Sort.DESCENDING))
                .flatMap(Observable::fromIterable)
                .map(TopicViewModel::new);
    }

    @Override
    protected RealmQuery<Topic> performQuery(Realm realm) {
        return realm.where(Topic.class)
                .equalTo(FIELD_NAME, ApiConstants.GROUP_ID);
    }

    @Override
    protected List<Topic> getQueryResult(Realm realm, RealmResults<Topic> results) {
        return realm.copyFromRealm(results);
    }
}
