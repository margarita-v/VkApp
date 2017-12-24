package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.DatabaseHelper;
import com.margarita.vk_app.consts.ApiConstants;
import com.margarita.vk_app.models.common.Member;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.models.view.profile.MemberViewModel;
import com.margarita.vk_app.mvp.view.BaseFeedView;
import com.margarita.vk_app.rest.api.GroupsApi;
import com.margarita.vk_app.rest.model.request.GroupsGetMembersRequest;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.Sort;

@InjectViewState
public class MembersPresenter extends BaseFeedPresenter<BaseFeedView, Member> {

    @Inject
    GroupsApi groupsApi;

    public MembersPresenter() {
        VkApplication.getApplicationComponent().inject(this);
        databaseHelper = new DatabaseHelper<Member>() {
            @Override
            public RealmQuery<Member> performQuery(Realm realm) {
                return realm.where(Member.class);
            }
        };
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return groupsApi.getMembers(
                new GroupsGetMembersRequest(ApiConstants.GROUP_CONTENT_ID, count, offset)
                        .toMap())
                // Convert Observable data from MemberGetResponse to Member
                .flatMap(full -> Observable.fromIterable(full.getResponse().getItems()))
                .doOnNext(DatabaseHelper::saveToDatabase)
                // Create MemberViewModel for every Member object
                .map(MemberViewModel::new);
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return Observable.fromCallable(
                databaseHelper.getListFromRealmCallable(Sort.ASCENDING))
                .flatMap(Observable::fromIterable)
                .map(MemberViewModel::new);
    }
}
