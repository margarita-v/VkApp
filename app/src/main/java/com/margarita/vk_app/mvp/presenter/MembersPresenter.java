package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.consts.ApiConstants;
import com.margarita.vk_app.models.common.Member;
import com.margarita.vk_app.models.view.BaseViewModel;
import com.margarita.vk_app.models.view.profile.MemberViewModel;
import com.margarita.vk_app.mvp.view.BaseFeedView;
import com.margarita.vk_app.rest.api.GroupsApi;
import com.margarita.vk_app.rest.model.request.GroupsGetMembersRequest;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class MembersPresenter extends BaseFeedPresenter<BaseFeedView, Member> {

    @Inject
    GroupsApi groupsApi;

    /**
     * Sort field for query to the database
     */
    private static final String SORT_FIELD = "id";

    public MembersPresenter() {
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return groupsApi.getMembers(
                new GroupsGetMembersRequest(ApiConstants.GROUP_ID, count, offset)
                        .toMap())
                // Convert Observable data from MemberGetResponse to Member
                .flatMap(full -> Observable.fromIterable(full.getResponse().getItems()))
                .doOnNext(this::saveToDatabase)
                // Create MemberViewModel for every Member object
                .map(MemberViewModel::new);
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .map(MemberViewModel::new);
    }

    @Override
    protected Callable<List<Member>> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            RealmResults<Member> results = realm.where(Member.class)
                    .findAllSorted(SORT_FIELD, Sort.ASCENDING);
            return realm.copyFromRealm(results);
        };
    }
}
