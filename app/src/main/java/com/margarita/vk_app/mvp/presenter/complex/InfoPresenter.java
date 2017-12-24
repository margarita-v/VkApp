package com.margarita.vk_app.mvp.presenter.complex;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.DatabaseHelper;
import com.margarita.vk_app.consts.ApiConstants;
import com.margarita.vk_app.models.common.Group;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.models.view.info.InfoContactsViewModel;
import com.margarita.vk_app.models.view.info.InfoLinksViewModel;
import com.margarita.vk_app.models.view.info.InfoStatusViewModel;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.view.BaseFeedView;
import com.margarita.vk_app.rest.api.GroupsApi;
import com.margarita.vk_app.rest.model.request.GroupsGetByIdRequest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;

@InjectViewState
public class InfoPresenter extends BaseFeedPresenter<BaseFeedView, Group>
        implements BaseFeedPresenter.ParsingInterface<Group> {

    @Inject
    GroupsApi groupsApi;

    public InfoPresenter() {
        VkApplication.getApplicationComponent().inject(this);
        databaseHelper = new DatabaseHelper<Group>() {
            @Override
            public RealmQuery<Group> performQuery(Realm realm) {
                return realm.where(Group.class);
            }
        };
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return groupsApi.getById(new GroupsGetByIdRequest(ApiConstants.GROUP_CONTENT_ID).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.getResponse()))
                .doOnNext(DatabaseHelper::saveToDatabase)
                .flatMap(group -> Observable.fromIterable(parseItemToList(group)));
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return Observable.fromCallable(
                databaseHelper.getItemFromRealmCallable(Math.abs(ApiConstants.GROUP_CONTENT_ID)))
                .flatMap(group -> Observable.fromIterable(parseItemToList(group)));
    }

    @Override
    public List<BaseViewModel> parseItemToList(Group item) {
        List<BaseViewModel> items = new ArrayList<>();
        items.add(new InfoStatusViewModel(item));
        items.add(new InfoContactsViewModel());
        items.add(new InfoLinksViewModel());
        return items;
    }
}