package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.consts.ApiConstants;
import com.margarita.vk_app.models.common.Topic;
import com.margarita.vk_app.models.view.base.BaseViewModel;
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
     * Field for query to the database
     */
    private static final String FIELD_NAME = "groupId";

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
                .doOnNext(Utils::saveToDatabase)
                // Create TopicViewModel for every Topic object
                .map(TopicViewModel::new);
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return Observable.fromCallable(
                getListFromRealmCallable(Sort.DESCENDING))
                .flatMap(Observable::fromIterable)
                .map(TopicViewModel::new);
    }

    @Override
    protected RealmQuery<Topic> performQuery(Realm realm) {
        return realm.where(Topic.class);
    }

    /*
        Override subquery method for getList method
        because we need all board items for concrete group ID
     */
    @Override
    protected RealmQuery<Topic> getListItems(Realm realm) {
        return getSingleItem(realm, ApiConstants.GROUP_ID);
    }

    @Override
    protected List<Topic> getQueryResult(Realm realm, RealmResults<Topic> results) {
        return realm.copyFromRealm(results);
    }

    @Override
    protected Topic getQueryResult(Realm realm, Topic result) {
        return realm.copyFromRealm(result);
    }

    @Override
    public String getFieldName() {
        return FIELD_NAME;
    }
}
