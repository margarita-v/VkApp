package com.margarita.vk_app.mvp.presenter.complex;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.DatabaseHelper;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.models.common.CommentItem;
import com.margarita.vk_app.models.common.Place;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.models.view.item.body.CommentBody;
import com.margarita.vk_app.models.view.item.footer.CommentFooter;
import com.margarita.vk_app.models.view.profile.CommentHeader;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.view.BaseFeedView;
import com.margarita.vk_app.rest.api.WallApi;
import com.margarita.vk_app.rest.model.request.owner.WallGetCommentsRequest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.Sort;

@InjectViewState
public class CommentsPresenter extends BaseFeedPresenter<BaseFeedView, CommentItem>
        implements BaseFeedPresenter.ParsingInterface<CommentItem> {

    /**
     * Place of comments
     */
    private Place place;

    @Inject
    WallApi wallApi;

    public CommentsPresenter() {
        VkApplication.getApplicationComponent().inject(this);
        databaseHelper = new DatabaseHelper<CommentItem>() {
            @Override
            public RealmQuery<CommentItem> performQuery(Realm realm) {
                return realm.where(CommentItem.class);
            }
        };
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
                .doOnNext(commentItem -> commentItem.setPlace(place))
                .doOnNext(DatabaseHelper::saveToDatabase)
                .flatMap(commentItem -> Observable.fromIterable(parseItemToList(commentItem)));
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return Observable.fromCallable(
                databaseHelper.getListFromRealmCallable(Sort.ASCENDING))
                .flatMap(Observable::fromIterable)
                .filter(commentItem -> commentItem.getPlace().equals(this.place) &&
                        !commentItem.isFromTopic())
                .flatMap(commentItem ->
                        Observable.fromIterable(parseItemToList(commentItem)));
    }

    @Override
    public List<BaseViewModel> parseItemToList(CommentItem item) {
        List<BaseViewModel> result = new ArrayList<>();
        result.add(new CommentHeader(item));
        result.add(new CommentBody(item));
        result.add(new CommentFooter(item));
        return result;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
