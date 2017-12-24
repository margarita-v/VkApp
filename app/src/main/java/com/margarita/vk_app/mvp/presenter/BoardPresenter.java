package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.DatabaseHelper;
import com.margarita.vk_app.consts.ApiConstants;
import com.margarita.vk_app.models.common.Topic;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.models.view.TopicViewModel;
import com.margarita.vk_app.mvp.view.BaseFeedView;
import com.margarita.vk_app.rest.api.BoardApi;
import com.margarita.vk_app.rest.model.request.group.BoardGetTopicsRequest;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;
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
        databaseHelper = new DatabaseHelper<Topic>() {
            @Override
            public RealmQuery<Topic> performQuery(Realm realm) {
                return realm.where(Topic.class);
            }

            @Override
            protected String getFieldName() {
                return FIELD_NAME;
            }

            /*
                Override subquery method for getList method
                because we need all board items for a concrete group ID
            */
            @Override
            public RealmQuery<Topic> getListItems(Realm realm) {
                return getSingleItem(realm, ApiConstants.GROUP_CONTENT_ID);
            }
        };
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return boardApi.getTopics(
                new BoardGetTopicsRequest(ApiConstants.GROUP_CONTENT_ID, count, offset)
                        .toMap())
                // Convert Observable data from TopicGetResponse to Topic
                .flatMap(full -> Observable.fromIterable(full.getResponse().getItems()))
                .doOnNext(topic -> topic.setGroupId(ApiConstants.GROUP_CONTENT_ID))
                .doOnNext(DatabaseHelper::saveToDatabase)
                // Create TopicViewModel for every Topic object
                .map(TopicViewModel::new);
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return Observable.fromCallable(
                databaseHelper.getListFromRealmCallable(Sort.DESCENDING))
                .flatMap(Observable::fromIterable)
                .map(TopicViewModel::new);
    }
}
